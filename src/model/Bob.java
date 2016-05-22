package model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import controller.UIController;
import utils.Config;

public class Bob {
	
	int r;

	private String cMessage; 
	
	Random sc = new SecureRandom();
	
	private BigInteger prime;
	
	private BigInteger base;
	
	private BigInteger privateKey;

	public BigInteger publicKey;
	
	public Bob () { }
	
	public BigInteger egDecrypt(BigInteger[] egEncrypt) {
		
		BigInteger C1 = egEncrypt[0];
		BigInteger C2 = egEncrypt[1];
		
        UIController.getInstance().out("C1: " + C1, 1);
        UIController.getInstance().out("C2: " + C2, 1);
		
        // Decryption
        BigInteger K = C1.modPow(getPrivateKey(), getPrime());        
        BigInteger KInverse = K.modInverse(getPrime());        
        BigInteger sol = C2.multiply(KInverse).mod(getPrime());
        UIController.getInstance().out("Decrypted: " + sol, 1);
		return sol;
	}
	
	public void setPublicKey() {
		publicKey = base.modPow(getPrivateKey(), getPrime());
	}
	public BigInteger getPublicKey() {
		return publicKey;
	}

	public BigInteger getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(BigInteger privateKey) {
		this.privateKey = privateKey;
	}
	public BigInteger getBase() {
		return base;
	}

	public void setBase(BigInteger b) {
		this.base = b;
	}
	
	public BigInteger getPrime() {
		return prime;
	}
	
	public void setPrime(BigInteger prime) {
		this.prime = prime;
	}


	public String getMessage() {
		return cMessage;
	}

	public void setMessage(String cMessage) {
		this.cMessage = cMessage;
	}


}
