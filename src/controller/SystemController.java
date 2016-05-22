package controller;

import java.math.BigInteger;
import java.util.ArrayList;

import model.Candidates;
import model.Voter;
import model.algorithm.ElGamal;
import model.algorithm.Paillier;
import model.db.Data;
import model.state.*;
import utils.Helpers;

public class SystemController {
	
	private static SystemController instance;
	
	private ArrayList<Voter> voterList = new ArrayList<Voter>();
	
	private String[] msgArray;
	private ArrayList <ElGamal> elGamalVotes;
	private Paillier pSystem;
	
	private IAppState appState;
	
	public static synchronized SystemController getInstance() {
		if (instance == null) {
			instance = new SystemController();
		}
		return instance;
	} 
	
	private SystemController(){
		setAppState(StateRegistration.getInstance());
		populateVoterList();
	}
	
	public void populateVoterList() {		
		ArrayList<String[]> vList = new ArrayList<String[]>(Data.getInstance().getDbArrayList());
		for (int i=0; i<vList.size(); i++) {			
			System.out.println(vList.get(i)[0]);			
			Voter voter = new Voter();
			voter.setFirstName(vList.get(i)[0]);
			voter.setLastName(vList.get(i)[1]);
			voter.setSecret(vList.get(i)[2]);
			voterList.add(voter);			
		}
	}
	
	public void setupVoter() {
		startAction();		
	}	

	public void doVoting() {
		
		//setAppState(StateVoting.getInstance());
		
		// five votes
		elGamalVotes = new ArrayList <ElGamal>();
		//pSystem = new Paillier();
		msgArray = new String[]{
				"10", 
				"10100",
				"0",
				"1000",
				"1001",
				"1010",
				"1100",
				"1010",
				"1"
				};


		for (int i=0; i<msgArray.length; i++) {			
			//UIController.getInstance().out("decimalValue: " + Integer.parseInt(msgArray[i], 2));    
			//convertStringToBinary(msgArray[i]);//could be used for mix net			
			//elGamalVotes.add(new ElGamal(msgArray[i]));
		}
		
		/*pSystem = new Paillier();
		pSystem.paillierGenerateKeys();
		pSystem.paillierHomomorphicAddition(msgArray);*/
    }
    
	public Boolean verifyVoterWithElGamal(String[] s) {

		//Check if all values encrypted/decrypted are equal...		
		//Encrypt All Strings
		elGamalVotes.clear();
		for (int i = 0; i < s.length; i++){
			ElGamal e = new ElGamal();
			e.setElGamal(s[i]);
			elGamalVotes.add(e);
		}
		
		//Let ElGamal compare the decryptions
		String[] decryptVals = new String[s.length];
		for (int i = 0; i < s.length; i++){
			decryptVals[i] = elGamalVotes.get(i).doElGamal();
		}
		if (Helpers.getInstance().diffValues(decryptVals) != 0) {
			return false;
		}			

		return true;		
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
    }
    
    private void convertStringToBinary(String msg) {    	
    	String s = msg;
    	byte[] bytes = s.getBytes();
    	StringBuilder binary = new StringBuilder();
    	for (byte b : bytes)
    	{
    		int val = b;
    		for (int i = 0; i < 8; i++)
    		{
    			binary.append((val & 128) == 0 ? 0 : 1);
    			val <<= 1;
    		}
    		binary.append(' ');
    	}
    	//System.out.println("'" + s + "' to binary: " + binary);    
    }
    
    public IAppState getAppState() {
    	return appState;
    }
    
    // state change
    public void setAppState(IAppState appState) {
    	//if(this.appState != null) this.appState.endAction(this);
    	this.appState = appState;
    }

	public void startAction()
	{
		appState.startAction(this);
	}

	public void endAction()
	{
		appState.endAction(this);
	}

    public ArrayList<ElGamal> getElGamal() {
    	return elGamalVotes;
    }
    
    public void setElGamal(ArrayList<ElGamal> elGamal) {
    	this.elGamalVotes = elGamal;
    }

	public ArrayList<Voter> getVoterList() {
		return voterList;
	}

	public void setVoterList(ArrayList<Voter> voterList) {
		this.voterList = voterList;
	}
	
}
