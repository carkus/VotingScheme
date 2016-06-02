package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Candidates;
import model.algorithm.ElGamal;
import model.algorithm.Paillier;
import model.state.StateTally;
import model.state.StateVoting;

public class TallyController {
	
	private static TallyController instance;

	private TallyController(){	

	}
	
	public static synchronized TallyController getInstance() {
		if (instance == null) {
			instance = new TallyController();
		}
		return instance;
	} 
	
	public void setTallyValues() {
		String[] s = SystemController.getInstance().getTallyArray();		
		for(int i = 0; i < s.length; i++) {
			UIController.getInstance().getTallyPanel().getTallyTextField()[i].setText(s[i]);
			UIController.getInstance().out(Candidates.getCArray()[i] + " : " + s[i], 2);
		}
		
				
	}
	
}
