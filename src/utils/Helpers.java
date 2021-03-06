package utils;

import java.math.BigInteger;
import java.util.ArrayList;

import controller.UIController;
import model.state.StateBallot;

public class Helpers {
	
	private static Helpers instance;
	
	public static synchronized Helpers getInstance() {
		if (instance == null) {
			instance = new Helpers();
		}
		return instance;
	} 
	
	private Helpers(){	}	
	
	public int diffValues(String[] s){
	    ArrayList<String> diffNum = new ArrayList<String>();
	    for(int i=0; i<s.length; i++){	        
	    	if(!diffNum.contains(s[i])){
	            diffNum.add(s[i]);
	        }	    	
	    }
	    return diffNum.size();
	}	
	
	public void resetApplication(){
		//setAppState(StateRegistration.getInstance());
		UIController.getInstance().clearOutputs();
		UIController.getInstance().getBallotPanel().resetBallot();
	}
	
	
}
