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
        
        UIController.getInstance().out("C1: " + egEncryption[0], 0);
        UIController.getInstance().out("C2: " + egEncryption[1], 0);
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
        
    	UIController.getInstance().out("ELGAMAL Encrypt: " + getMessage(), 0);
    	UIController.getInstance().out("Public Key: " + bob.getPublicKey(), 1);
    	UIController.getInstance().out("Private Key: " + bob.getPrivateKey() , 1);
    	UIController.getInstance().out("Generator: " + bob.getBase() , 1); 
    	UIController.getInstance().out("", 1); 
    	
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
		BigInteger p = BigInteger.probablePrime(Config.getBitSize(), sc);
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
     * Generate one of the public key, p, where p is a positive prime number.
     * The p will be range from 1 to the given max value.
     */
    public void getPrime() {
        //p = Prime.generatePrime(1, maxValue.longValue());
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
     * Generate one of the public key, r, where r = g^a (mod p)
     */
    public void generateR() {
        //r = base.modPow(sk, p);
    }
    
    /**
     * Generate a random number k, where 0 < k < p
     */
    /*public void generateK() {        
        Random random = new Random();        
        while (true) {            
            long n = 1 + ((long) (random.nextDouble() * p.doubleValue()));            
            k = new BigInteger(Long.toString(n));            
            if (k.compareTo(p) < 0) {
            	break;            	
            }
        }
    }*/
    
    /** 
     * Encrypt the given message by using ElGamal algorithm. This algorithm
     * will encrypt two characters at once, and ONLY encrypt alphabetic letters.
     * This encryption will append an 'X' to the given message if the given 
     * message has an odd in length.
     * @param message the message to be encrypted
     * @return the cipher text, in the format of "1234, 5678"
     */
    public String encrypt(String message) {
                        
        // Remove all non-alphanumeric characters
        //message = message.replaceAll("[^a-zA-Z0-9]", "");
        
        //boolean isOdd = (message.length() & 1) == 1 ? true : false;
        
        // Append an 'X' if the message has an odd length
        /*if (isOdd) {
        	message = message + 'X';        	
        }*/
        
        //boolean randomK = false;
        
        // Randomize k in each block if no k was given
        /*if (k == null) {
            randomK = true;
        }*/
        
        StringBuilder result = new StringBuilder();
        
        int i = 0;
        while (i < message.length()) {
            
            // Get two characters at once
            String str = message.substring(i, i+=2);
            
            String number = Convert.stringToNumber(str);
            
            /*if (!number.matches("[0-9]+")) 
                return "Invalid message input.\n"+
                       "Message must contains only alphabetic letters.";*/
            
            /*if (randomK)
                generateRandom();*/
            
            // Encrypt format: (firstPart, secondPart)
            
            
            /*BigInteger firstPart = base.modPow(k, p);
            BigInteger secondPart = r.modPow(k, p);
            secondPart = secondPart.multiply(new BigInteger(number));
            secondPart = secondPart.mod(p);
            
            result.append(firstPart + ", " + secondPart + "\n");*/
        }
        
        return result.toString();
    }
    
    /**
     * Decrypt the given cipher text by using ElGamal algorithm. The cipher
     * text should only contain numeric characters.
     * @param cipherText the text to be decoded
     * @return the original message in the form of two characters per block
     */
    /*public String decrypt(String cipherText) {
        
        cipherText = cipherText.trim().replaceAll("[^a-zA-Z0-9,\\s]", "");
        cipherText = cipherText.replaceAll(",", " ");
        
        String[] texts = cipherText.split("\\s+");
        
        // If the given cipherText has invalid format, then return null
        if ((texts.length & 1) == 1)
            return "Invalid cipher text format.\n"+
                   "Format must be:\n"+
                   "    (1234, 5678)";
        
        // To store the result
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < texts.length; i++) {
            
            String firstPart = texts[i];
            String secondPart = texts[++i];
            
            // If any given string contain non numeric character, then return null
            if (!firstPart.matches("[0-9]+") || !secondPart.matches("[0-9]+"))
                return "Invalid cipher text input.\n"+
                       "Cipher text must contains only numeric letters.";
            
            BigInteger pMinus2 = p.subtract(new BigInteger("2"));
            BigInteger gPowerK = new BigInteger(firstPart).modPow(pMinus2, p);
            BigInteger powerA = gPowerK.modPow(sk, p);
            BigInteger result = new BigInteger(secondPart).multiply(powerA);
            result = result.mod(p);
            
            // Convert the result from number to alphabetic letters
            output.append(Convert.numberToString(result.toString()) + "\n");
        }
        
        return output.toString();
    }*/
    
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
