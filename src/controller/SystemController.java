package controller;

import java.util.ArrayList;

import model.Candidates;
import model.algorithm.ElGamal;
import model.algorithm.Paillier;
import model.state.*;

public class SystemController {
	
	private static SystemController instance;
	
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
	}
	
	public void setupVoter() {
		startAction(this);		
	}	

	public void doVoting() {
		
		setAppState(StateVoting.getInstance());
		
		// five votes
		elGamalVotes = new ArrayList <ElGamal>();
		pSystem = new Paillier();
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
		
		pSystem.paillierGenerateKeys();
		pSystem.paillierHomomorphicAddition(msgArray);
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
    	System.out.println("'" + s + "' to binary: " + binary);    
    }
    
    public IAppState getAppState() {
    	return appState;
    }
    
    // state change
    public void setAppState(IAppState appState) {
    	this.appState = appState;
    }

	public void startAction(SystemController s)
	{
		appState.startAction(this);
	}

	public void endAction(SystemController s)
	{
		appState.endAction(this);
	}	
    

    public ArrayList<ElGamal> getElGamal() {
    	return elGamalVotes;
    }
    
    public void setElGamal(ArrayList<ElGamal> elGamal) {
    	this.elGamalVotes = elGamal;
    }
	
}
