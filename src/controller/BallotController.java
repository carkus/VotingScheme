package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JCheckBox;

import model.Candidates;
import model.algorithm.Paillier;
import model.state.StateTally;
import utils.Config;

public class BallotController {
	
	private static BallotController instance;
	
	//private ArrayList <ElGamal> elGamalVotes;
	private ArrayList <Paillier> paillierVotes = new ArrayList<Paillier>();
	
	private int pref = Config.getREQUIRED_CANDIDATES();

	private BallotController(){
		resetCandidateString();
	}
	
	public static synchronized BallotController getInstance() {
		if (instance == null) {
			instance = new BallotController();
		}
		return instance;
	} 
	public void performVotingProcess() {		
		String[] allVotesArray = collateVotes();
		String[] authorityVotes = generateVoteAuthorities(allVotesArray);
		ArrayList <Paillier> encryptedArray = encryptVotes(authorityVotes);
		TallyController.getInstance().setEncryptedVotes(encryptedArray);		
		printVotes(allVotesArray);		
		SystemController.getInstance().changeState(StateTally.getInstance());
	}
	
	public void printVotes(String[] votes) {
		UIController.getInstance().out("\nPaillier for authorities success", 0);
		UIController.getInstance().out("Votes sent:", 0);
		for (int i=0; i<votes.length; i++) {
			UIController.getInstance().out("VOTE " + (1+i) + " : " + votes[i], 0);
		}
	}
	public String[] collateVotes() {
		int voterCount = Config.getVOTERS();
		String[] voteArray = new String[voterCount];
		voteArray[0] = Candidates.getInputString();
		for (int i=1; i<voterCount; i++) {
			voteArray[i] = generateDummyVote(0);
		}
		//Shuffle before sending to Paillier to simulate MixNet anonymity
		return voteArray;
	}
	
	public String[] generateVoteAuthorities(String[] votes) {
		UIController.getInstance().clearOutputs();
		String[] votingAuthorities = new String[Candidates.getCArray().length];

		for(int i=0; i<votingAuthorities.length; i++){
			//Get candidate vote for EACH vote
			//Each Authority handles a single candidate
			StringBuilder s = new StringBuilder();
			for(int j=0; j<votes.length; j++){
				String vote = votes[j];
				char add = vote.charAt(i);
				s.append(add);
			}
			votingAuthorities[i] = String.valueOf(s);
			simulateMixNet(votingAuthorities[i]);
		}
		return votingAuthorities;
    }
	public ArrayList <Paillier> encryptVotes(String[] votes) {
		//System creates a new Paillier for each of the Authorities
		//So the system can track the keys generated
		System.out.println("Candidates: " + votes.length);
		for(int i=0; i<votes.length; i++){
			Paillier pSystem = new Paillier();
			pSystem.paillierGenerateKeys();
			pSystem.setpIndex(i);	
			//Perform Paillier Additive Homomorphism on each candidate value:
			pSystem.paillierEncryptVotes(votes[i]);
			paillierVotes.add(pSystem);
		}
		return paillierVotes;
	}

	public String simulateMixNet(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
	
	public String generateDummyVote(int a) {
		StringBuilder whole = new StringBuilder();
		String[] bits = new String[Candidates.getCArray().length];
		int pref = Config.getREQUIRED_CANDIDATES();
		Random r = new Random();
		//Populate 'unselected' array  
		for(int i=0; i<Candidates.getCArray().length; i++){
			bits[i] = "0";
		}
		//Iterate till populated with preferences 
		while (pref > 0){ 
			int ri = r.nextInt(Candidates.getCArray().length);
			if (bits[ri] == "0") {
				bits[ri] = String.valueOf(pref);
				pref --;
			}
		}
		//Easy concatenate
		for(int i=0; i<bits.length; i++){
			whole.append(bits[i]);
		}
		return whole.toString();
	}
	
	public int checkSelectionCount(JCheckBox[] cbs) {
		int checked = 0;
		for(int i=0; i<cbs.length; i++){
			if (cbs[i].isSelected()) checked++;
		}
		return checked;
    }
	
	public void setVote(JCheckBox[] cbs, int i) {
		cbs[i].setEnabled(false);
		updateCandidateString(i, pref);
		int checked = checkSelectionCount(cbs);
		setPref(Config.getREQUIRED_CANDIDATES()-checked);
	}
	
	public void resetCandidateString() {
		StringBuilder sb = new StringBuilder(Candidates.getCArray().length);
		// adds 9 character string at beginning
		for (String c : Candidates.getCArray()) {
			sb.append("0");
		}
		Candidates.setInputString(sb.toString());
	}
	
    public void updateCandidateString(int i, int p) {
    	StringBuilder s = new StringBuilder(Candidates.getInputString());
    	String v = String.valueOf(p);
    	s.setCharAt(i, v.charAt(0));
    	Candidates.setInputString(s.toString());
    	UIController.getInstance().out("Current Vote: " + Candidates.getInputString(), 0);
    }	
	
    public ArrayList<Paillier> getPaillierVotes() {
    	return paillierVotes;
    }
    
    public void setPaillierVotes(ArrayList<Paillier> paillierVotes) {
    	this.paillierVotes = paillierVotes;
    }
    
	public int getPref() {
		return pref;
	}
	
	public void setPref(int pref) {
		this.pref = pref;
	}
    
}
