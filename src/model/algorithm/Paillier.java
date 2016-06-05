package model.algorithm;
/**
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version. 
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for 
 * more details. 
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.math.*;
import java.util.*;

import controller.UIController;
import utils.Config;

public class Paillier {

    /**
     * Encrypted Mesage Array is what is sent to the Tally Controller
     * i.e. this is the Pallier process of the current vote Authority
     * Each vote Authority gets their own encryption.
     * This is to ensure NO Authority has knowledge of each other.
     *  
     */
	private BigInteger[] emArray;  
	private int pIndex;

	/**
     * p and q are two large primes. 
     * lambda = lcm(p-1, q-1) = (p-1)*(q-1)/gcd(p-1, q-1).
     */
    private BigInteger p,  q,  lambda;
    
    /**
     * n = p*q, where p and q are two large primes.
     */
    public BigInteger n;
    
    /**
     * nsquare = n*n
     */
    public BigInteger nsqr;
    
    /**
     * a random integer in Z*_{n^2} where gcd (L(g^lambda mod n^2), n) = 1.
     */
    private BigInteger g;
    
    /**
     * number of bits of modulus
     */
    private int bitLength;

    public Paillier() {}

    /**
     * main function
     * @param str string Array of votes
     */
    public void paillierGenerateKeys() {
    	//Get all keys based on bit length and prime certainty:
    	KeyGeneration(Config.getPaillierBitSize(), Config.getPrimeCertainty());
    }
    
    public void paillierEncryptVotes(String str) {
    	//Don't continue if empty.
    	if (str.length() == 0) return;
    	//Create new Pallier Array of Encryptions
    	emArray = new BigInteger[str.length()];
    	
    	for (int i = 0; i < str.length(); i++) {	
    		String vote = String.valueOf(str.charAt(i));
    		emArray[i] = Encryption(new BigInteger(vote));
    		UIController.getInstance().out("SEND Candidate: " + (1+this.getpIndex()) + "  Encrypted Vote " + (1+i), 0);
    		UIController.getInstance().out(emArray[i].toString(), 1);
    	}
    	
    }

    /**
     * Gets the ball rolling.
     */
    public void generateKeys(int bits, int cert) {
        KeyGeneration(bits, cert);
    }
    
    /**
     * Sets up the public key and private key.
     * @param bitLengthVal number of bits of modulus.
     * @param certainty The probability that the new BigInteger represents a prime number will exceed (1 - 2^(-certainty)). The execution time of this constructor is proportional to the value of this parameter.
     */
    public void KeyGeneration(int bitLengthVal, int certainty) {
        bitLength = bitLengthVal;
        
        /*Constructs two randomly generated positive BigIntegers that are probably prime, with the specified bitLength and certainty.*/
        p = new BigInteger(bitLength / 2, certainty, new Random());
        q = new BigInteger(bitLength / 2, certainty, new Random());

        n = p.multiply(q);
        nsqr = n.multiply(n);

        g = new BigInteger("2");
        
        //Carmichael's function:
        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(
        		p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
        
        /* check whether g is good.*/
        if (g.modPow(lambda, nsqr).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
        	UIController.getInstance().out("g generator failed (!= 1). Regenerating...", 0);
        	UIController.getInstance().out("", 0);
            //System.exit(1);//failed
            KeyGeneration(bitLength, certainty);
        }        
        
        /*
         * Output all generated keys to the output panel before encryption 
         */
        UIController.getInstance().out("\nPallier Key generation @ " + ((Integer) bitLength).toString() + "-bits", 0);
        UIController.getInstance().out("  p      : " + p.toString(), 0);
        UIController.getInstance().out("  q      : " + q.toString(), 0);
        UIController.getInstance().out("  n      : " + n.toString(), 0);
        UIController.getInstance().out("  n^2    : " + nsqr.toString(), 0);
        UIController.getInstance().out("  lambda : " + lambda.toString(), 0);
    }

    /**
     * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function automatically generates random input r (to help with encryption).
     * @param m plaintext as a BigInteger
     * @return ciphertext as a BigInteger
     */
    public BigInteger Encryption(BigInteger m) {
        BigInteger r = new BigInteger(bitLength, new Random());
        return g.modPow(m, nsqr).multiply(r.modPow(n, nsqr)).mod(nsqr);
    }

    /**
     * Decrypts ciphertext c. plaintext m = L(c^lambda mod n^2) * u mod n, where u = (L(g^lambda mod n^2))^(-1) mod n.
     * @param c ciphertext as a BigInteger
     * @return plaintext as a BigInteger
     */
    public BigInteger Decryption(BigInteger c) {
        BigInteger mu = g.modPow(lambda, nsqr).subtract(BigInteger.ONE).divide(n).modInverse(n);
        return c.modPow(lambda, nsqr).subtract(BigInteger.ONE).divide(n).multiply(mu).mod(n);
    }

	public BigInteger[] getEmArray() {
		return emArray;
	}

	public void setEmArray(BigInteger[] emArray) {
		this.emArray = emArray;
	}
	
    public int getpIndex() {
		return pIndex;
	}

	public void setpIndex(int pIndex) {
		this.pIndex = pIndex;
	}
}