package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Candidates;
import model.algorithm.ElGamal;
import model.algorithm.Paillier;
import model.state.StateTally;
import model.state.StateVoting;

public class TallyController {
	
	private static TallyController instance;
	private ArrayList <Paillier> eVotes;

	private ArrayList<String> tallyList = new ArrayList<String>();;
	
	private TallyController(){}
	
	public static synchronized TallyController getInstance() {
		if (instance == null) {
			instance = new TallyController();
		}
		return instance;
	} 
	
	public void setTallyValues() {
		for(int i = 0; i < tallyList.size(); i++) {
			UIController.getInstance().getTallyPanel().getTallyTextField()[i].setText(tallyList.get(i));
		}		
	}
	
	public void doTallyProcess() {
		for(int i = 0; i <eVotes.size(); i++) {
			tallyList.add(paillierDecryptAndSum(eVotes.get(i)));
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
    		esum = esum.multiply(emA[i]).mod(p.nsqr);
    	}
    	String tally = p.Decryption(esum).toString();
    	UIController.getInstance().out("Decrypted tally for Candidate " + p.getpIndex() + " (" + Candidates.getCArray()[p.getpIndex()] + ") = " + tally, 2);
    	return tally;    	
    }		
	
	public ArrayList<Paillier> getEncryptedVotes() {
		return eVotes;
	}
	
	public void setEncryptedVotes(ArrayList<Paillier> encryptedVotes) {
		this.eVotes = encryptedVotes;
	}
}
