package controller;

import java.util.ArrayList;

import model.Voter;
import model.algorithm.ElGamal;
import utils.Helpers;

public class RegistrationController {
	
	private static RegistrationController instance;
	
	private ArrayList <ElGamal> elGamalVotes;

	private RegistrationController(){	
		elGamalVotes = new ArrayList <ElGamal>();
	}
	
	public static synchronized RegistrationController getInstance() {
		if (instance == null) {
			instance = new RegistrationController();
		}
		return instance;
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
		if (Helpers.getInstance().diffValues(decryptVals) != 1) {
			return false;
		}
		return true;		
	}	
	
	public Boolean verifyVoterUniqueness(String[] s) {
		//Check database for previous vote
		ArrayList<Voter> voterList = new ArrayList<Voter>(SystemController.getInstance().getVoterList());
		//Check id against database through encrypted value
		ElGamal e = new ElGamal();
		e.setElGamal(s[0]);
		elGamalVotes.add(e);
		if (voterList.get(Integer.parseInt(elGamalVotes.get(0).doElGamal())).getHasVoted() != false) {
			return false;
		}
		return true;		
	}	
	
}
