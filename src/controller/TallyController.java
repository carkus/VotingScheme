package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import model.Candidates;
import model.algorithm.ElGamal;
import model.algorithm.Paillier;
import model.state.StateTally;
import model.state.StateVoting;

public class TallyController {
	
	private static TallyController instance;
	private ArrayList <Paillier> eVotes;

	private TallyController(){}
	
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
	
	public void doTallyProcess() {
		for(int i = 0; i <eVotes.size(); i++) {
			paillierDecryptAndSum(eVotes.get(i));
		}
	}	
	
	
	/*
	* This is where the receiver to decrypt and tallys the encryptions.
	* Uses Pallier instance and polymorphism to simulate Cloud deliverence.
	* ADDITIVE homomorphic properties -> D( E(m1)*E(m2) mod n^2) = (m1 + m2) mod n
	* Get unencrypted sum AND encrypted product of all messages
	* Return decrypted encrypted product (using log) to set up proof of polymorphism
	*/	
	public String paillierDecryptAndSum(Paillier p) {
		
		BigInteger[] emA = p.getEmArray();
		BigInteger esum = new BigInteger(emA[0].toString());
		
    	for (int i = 1; i < emA.length; i++) {
    		//System.out.println("EMA : " + emA[i]);
    		esum = esum.multiply(emA[i]).mod(p.nsqr);
    	}	
    	UIController.getInstance().out("Decrypted tally for Candidate " + p.getpIndex() + " (" + Candidates.getCArray()[p.getpIndex()] + ") = " + p.Decryption(esum).toString(), 2);
    	return "done";
    	
    }		
	
	public ArrayList<Paillier> getEncryptedVotes() {
		return eVotes;
	}
	
	public void setEncryptedVotes(ArrayList<Paillier> encryptedVotes) {
		this.eVotes = encryptedVotes;
	}
}
