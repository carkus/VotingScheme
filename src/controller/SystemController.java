package controller;

import java.util.ArrayList;

import model.Voter;
import model.db.Data;
import model.state.*;

public class SystemController {
	
	private static SystemController instance;
	
	private ArrayList<Voter> voterList = new ArrayList<Voter>();
	
	private String[] tallyArray;
			
	private IAppState appState;
	
	public static synchronized SystemController getInstance() {
		if (instance == null) {
			instance = new SystemController();
		}
		return instance;
	} 
	
	private SystemController(){
		setAppState(StateVoting.getInstance());
		populateVoterList();
	}
	
	public void populateVoterList() {		
		ArrayList<String[]> vList = new ArrayList<String[]>(Data.getInstance().getDbArrayList());
		for (int i=0; i<vList.size(); i++) {			
			Voter voter = new Voter();
			voter.setFirstName(vList.get(i)[0]);
			voter.setLastName(vList.get(i)[1]);
			voter.setSecret(vList.get(i)[2]);
			voter.setHasVoted(Boolean.parseBoolean(vList.get(i)[3]));
			voterList.add(voter);			
		}
	}

	public void setupVoter() {
		startAction();
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
    
    public void changeState(IAppState appState) {		
		SystemController.getInstance().endAction();
		SystemController.getInstance().setAppState(appState);
		SystemController.getInstance().startAction();
    }
    
    // state change
    public void setAppState(IAppState appState) {
    	//if(this.appState != null) this.appState.endAction(this);
    	this.appState = appState;
    }

	public void startAction()
	{
		appState.startAction(this);
		UIController.getInstance().getInstructionPanel().setInnerText(appState.getInstructions());
	}

	public void endAction()
	{
    	UIController.getInstance().out("\n\n", 0);
    	UIController.getInstance().out("\n\n", 1);
		appState.endAction(this);
	}

	public ArrayList<Voter> getVoterList() {
		return voterList;
	}

	public void setVoterList(ArrayList<Voter> voterList) {
		this.voterList = voterList;
	}

	public String[] getTallyArray() {
		return tallyArray;
	}

	public void setTallyArray(String[] tallyArray) {
		this.tallyArray = tallyArray;
	}
	
}
