package model.algorithm;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import controller.UIController;
import model.Alice;
import model.Bob;
import model.Convert;
import utils.Config;


public class ElGamal {

    private String message;	// the message

	private BigInteger maxValue;	// max value of certain keys
	private BigInteger[] egEncryption;
	
    private Bob bob;
    private Alice alice;
            
    /**
     * Default constructor. 
     * @param String message to encrypt
     */
    public ElGamal() {
        super();
    }
    
    public BigInteger[] setElGamal(String M) {
    	System.out.println("Message: " + M);        
    	setMessage(M);
    	bob = buildBob();
        alice = buildAlice();
        egEncryption = alice.doEncryption(getMessage());
        
        UIController.getInstance().out(egEncryption[0] + ", " + egEncryption[1], 1);
        
        UIController.getInstance().out("", 0);
        return egEncryption;
    }
    
    public String doElGamal() {    	
    	BigInteger decryption = bob.egDecrypt(egEncryption);
    	UIController.getInstance().out("", 0);  
    	String s = decryption.toString();
    	return s;
    }

    private Bob buildBob() {
        bob = new Bob();        
        bob.setPrime(getRandomPrime());
        bob.setBase(generateRandom(bob.getPrime()));
        bob.setPrivateKey(getSecretKey());  
        bob.setPublicKey();
        
        UIController.getInstance().out("ELGAMAL  Encrypt: " + getMessage(), 0);
    	UIController.getInstance().out("RECEIVE  Public Key: " + bob.getPublicKey(), 0);
    	UIController.getInstance().out("RECEIVE  Generator: " + bob.getBase() , 0);     	
    	UIController.getInstance().out("__private__: " + bob.getPrivateKey() , 2);
    	
        return bob;
    }
    
    private Alice buildAlice() {
    	alice = new Alice();
    	alice.setBobBase(bob.getBase());
    	alice.setBobPrime(bob.getPrime());
    	alice.setBobPublicKey(bob.getPublicKey());    	
        return alice;
    }
	
	private BigInteger getRandomPrime() {
		Random sc = new SecureRandom();
		BigInteger p = BigInteger.probablePrime(Config.getElgamalBitSize(), sc);
		return p;
	}    
    
    /**
     * Generate the secret key, sk. 
     * The key will be ranged from 1 to the maximum value.
     */
    public BigInteger getSecretKey() {
        Random random = new Random();       
        int number = random.nextInt(Config.getMaxHigh());
        return new BigInteger(Integer.toString(number)); 
    }
    
    /**
     * Generate the public key g, where g is a random number between 1 and p.
     */
    public BigInteger generateRandom(BigInteger p) {        
        Random random = new Random();     
        BigInteger r;
        while (true) {            
            long n = 1 + ((long) (random.nextDouble() * p.doubleValue()));            
            r = new BigInteger(Long.toString(n));
            if (r.compareTo(p) < 0) {
            	break;            	
            }
        }
        return r;
    }

    /**
     * Set the maximum value for certain keys.
     * @param mv maximum value
     */
    public void setMaxValue(String mv) {
        this.maxValue = new BigInteger(mv);
    }
    
    /**
     * Get the maximum value for certain keys.
     * @return maximum value
     */
    public String getMaxValue() {
        return maxValue.toString();
    }
    
    public String getMessage() {
    	return message;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }
    /**
     * Get the private key a.
     * @return the private key a
     */
    public String getBobPrivateKey() {
        return bob.getPrivateKey().toString();
    }
    
    /**
     * Get the private key k.
     * @return the private key k
     */
    public String getBobPublicKey() {
        return bob.publicKey.toString();
    }

}
