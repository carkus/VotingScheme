package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Candidates;
import model.algorithm.ElGamal;
import model.algorithm.Paillier;
import model.state.StateTally;
import model.state.StateVoting;

public class BallotController {
	
	private static BallotController instance;
	
	private ArrayList <ElGamal> elGamalVotes;
	
	private int voteCount = 100;
	private String[] resultArray;
	private Paillier pSystem;		

	private BallotController(){	
		elGamalVotes = new ArrayList <ElGamal>();
		populateCandidateString();
		generateDummyVote(0);
	}
	
	public static synchronized BallotController getInstance() {
		if (instance == null) {
			instance = new BallotController();
		}
		return instance;
	} 
	public void performVotingProcess() {
		resultArray = collateVotes();
		resultArray = processVotes(resultArray);
		tallyVotes(resultArray);
	}
	
	public String[] collateVotes() {
		String[] voteArray = new String[voteCount];
		voteArray[0] = Candidates.getInputString();
		for (int i=1; i<voteCount; i++) {
			voteArray[i] = generateDummyVote(0);
		}		
		for (int a=0; a<voteArray.length; a++) {
			System.out.println(a + " : " + voteArray[a]);
		}
		return voteArray;
	}
	
	public String[] processVotes(String[] s) {
		
		String[] submit = new String[s.length];
		String[] results = new String[Candidates.getCArray().length];
		int index = 0;
				
		//string length
		for(int i=0; i<Candidates.getCArray().length; i++){
			//vote count
			for(int j=0; j<s.length; j++){
				String vote = s[j];
				char add = vote.charAt(index);
				submit[j] = String.valueOf(add);
			}
			pSystem = new Paillier();
			pSystem.paillierGenerateKeys();			
			results[index] = pSystem.paillierHomomorphicAddition(submit);
			index++;			
		}
		return results;
    }    	
	
	public void tallyVotes(String[] s) {
		SystemController.getInstance().setTallyArray(s);
		SystemController.getInstance().changeState(StateTally.getInstance());
	}
	
	public String generateDummyVote(int a) {
		String bits = "";
		int votes = 0;
		Random r = new Random(); 
		for(int i=0; i<Candidates.getCArray().length; i++){ 
			int x = 0; 
			if (r.nextBoolean() && votes < 3) {
				x = 1;
				votes++;
			}
			bits += x; 		
		} 
		System.out.println(bits); 
		return bits;			
	}	
	
	public void populateCandidateString() {
    	StringBuilder sb = new StringBuilder(Candidates.getCArray().length);
    	// adds 9 character string at beginning
    	for (String c : Candidates.getCArray()) {
    		sb.append("0");
    	}
    	Candidates.setInputString(sb.toString());
    }
	
    public void updateCandidateString(int i) {
    	StringBuilder s = new StringBuilder(Candidates.getInputString());
    	s.setCharAt(i, '1');
    	Candidates.setInputString(s.toString());
    	UIController.getInstance().out("Current Vote: " + Candidates.getInputString(), 0);
    }	
	
}
