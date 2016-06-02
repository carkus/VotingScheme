package controller;

import java.util.ArrayList;

import model.Candidates;
import model.Voter;
import model.algorithm.ElGamal;
import model.db.Data;
import model.state.StateVoting;
import utils.Helpers;
import view.RegistrationPanel;

public class RegistrationController {
	
	private static RegistrationController instance;
	
	private ArrayList <ElGamal> elGamalVals;

	private RegistrationController(){	
		elGamalVals = new ArrayList <ElGamal>();
	}
	
	public static synchronized RegistrationController getInstance() {
		if (instance == null) {
			instance = new RegistrationController();
		}
		return instance;
	} 
	
	public Boolean verifyVoterWithElGamal(String[] s) {

		UIController.getInstance().out("Verifying voter registration.", 0);
		
		//Check if all values encrypted/decrypted are equal...
		//Encrypt All Strings
		elGamalVals.clear();
		
		//e(m1)*e(m2) = e(m1*m2);
		//Separate ElGamal
		for (int i = 0; i < s.length; i++){
			ElGamal e = new ElGamal();
			e.setElGamal(s[i]);
			elGamalVals.add(e);
		}
		
		//Multiplicated ElGamel
		/*int eProd = 1;
		ElGamal e = new ElGamal();		
		for (int i = 0; i < s.length; i++){
			eProd *= Integer.parseInt(s[i]);
			System.out.println("EPROD: " + eProd);
		}
		e.setElGamal(((Integer)eProd).toString());
		elGamalVals.add(e);*/	
			
		//Let ElGamal compare the decryptions
		String[] decryptVals = new String[s.length];
		for (int i = 0; i < s.length; i++){
			decryptVals[i] = elGamalVals.get(i).doElGamal();
		}
		if (Helpers.getInstance().diffValues(decryptVals) != 1) {
			return false;
		}
		return true;		
	}	
	
	public Boolean verifyVoterUniqueness(String[] s) {
		UIController.getInstance().out("Verifying voter uniqueness.", 0);
		//Check database for previous vote
		ArrayList<Voter> voterList = new ArrayList<Voter>(SystemController.getInstance().getVoterList());
		//Check id against database through encrypted value
		ElGamal e = new ElGamal();
		e.setElGamal(s[0]);
		elGamalVals.add(e);
		if (voterList.get(Integer.parseInt(elGamalVals.get(0).doElGamal())).getHasVoted() != false) {
			return false;
		}
		return true;		
	}	
	public ArrayList<Voter> closeVoterAccess(ArrayList<Voter> vl, int i) {
		vl.get(i).setHasVoted(true);
		return vl;
	}	
	
	public void checkRegistrationServer(String[] s) {
		//send to ElGamal for confirmation
		
		UIController.getInstance().clear(0);
		UIController.getInstance().clear(1);
		UIController.getInstance().clear(2);	
		
		UIController.getInstance().getRegistrationPanel().getRegOutput().setText("");
		if (!RegistrationController.getInstance().verifyVoterUniqueness(s)){
			String msg = "Voter has already voted";
			UIController.getInstance().getRegistrationPanel().getRegOutput().setText(msg);
			return;
		}
		if(!RegistrationController.getInstance().verifyVoterWithElGamal(s)){
			String msg = "Registration Unsuccessful.  (incorrect voter details)";
			UIController.getInstance().getRegistrationPanel().getRegOutput().setText(msg);
			return;
		} 
		SystemController.getInstance().setVoterList(closeVoterAccess(SystemController.getInstance().getVoterList(), Integer.parseInt(s[0])));
		SystemController.getInstance().changeState(StateVoting.getInstance());
		UIController.getInstance().out("\nVoter found.", 0);		
	}
	
}
