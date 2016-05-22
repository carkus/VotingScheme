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

/**
 * Paillier Cryptosystem <br><br>
 * References: <br>
 * [1] Pascal Paillier, "Public-Key Cryptosystems Based on Composite Degree Residuosity Classes," EUROCRYPT'99.
 *    URL: <a href="http://www.gemplus.com/smart/rd/publications/pdf/Pai99pai.pdf">http://www.gemplus.com/smart/rd/publications/pdf/Pai99pai.pdf</a><br>
 * 
 * [2] Paillier cryptosystem from Wikipedia. 
 *    URL: <a href="http://en.wikipedia.org/wiki/Paillier_cryptosystem">http://en.wikipedia.org/wiki/Paillier_cryptosystem</a>
 * @author Kun Liu (kunliu1@cs.umbc.edu)
 * @version 1.0
 */
public class Paillier {

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

    public Paillier() {
    	
    }

    /**
     * main function
     * @param str string Array of votes
     */
    public void paillierGenerateKeys() {
    	

    	//Get all keys based on bit length and prime certainty:
    	KeyGeneration(Config.getBitSize(), Config.getPrimeCertainty());
    	
    	/*
    	* Test MULTIPLICATIVE homomorphic properties -> D(E(m1)^m2 mod n^2) = (m1*m2) mod n
    	* Get unencrypted sum AND encrypted product of all messages
    	* Return decrypted encrypted product (using log) to set up proof of polymorphism
    	*/
    	/*BigInteger prod = new BigInteger(mA[0].toString());    	
    	BigInteger eprod = new BigInteger(emA[0].toString());
    	
    	for (int i = 1; i < 2; i++) {
    		prod = prod.multiply(mA[i]);
    		eprod = eprod.modPow(mA[i], nsqr);	
    	}
    	
    	UIController.getInstance().out("original prod: " + prod.toString());
    	UIController.getInstance().out("decrypted prod: " + Decryption(eprod).toString());*/
    	
    }
    
    public void paillierHomomorphicAddition(String[] str) {
    	
    	//Don't continue if empty.
    	if (str.length == 0) return;
    	
    	//Do encryption:
    	UIController.getInstance().out("Votes cast: " + str.length, 1);
    	BigInteger[] mA = new BigInteger[str.length]; 
    	BigInteger[] emA = new BigInteger[str.length];    	
    	
    	for (int i = 0; i < str.length; i++) {
    		mA[i] = new BigInteger(str[i]);
    		UIController.getInstance().out("Vote " + (i+1) + " : " + str[i].toString(), 1);    		
    		emA[i] = Encryption(mA[i]);
    		UIController.getInstance().out("Encrypted Vote " + (i+1) + " : " + emA[i].toString(), 1);
    		UIController.getInstance().out("", 1);    		
    	}
    	
    	/*
    	* Test ADDITIVE homomorphic properties -> D( E(m1)*E(m2) mod n^2) = (m1 + m2) mod n
    	* Get unencrypted sum AND encrypted product of all messages
    	* Return decrypted encrypted product (using log) to set up proof of polymorphism
    	*/
    	BigInteger sum = new BigInteger(mA[0].toString());    	
    	BigInteger esum = new BigInteger(emA[0].toString());
    	for (int i = 1; i < str.length; i++) {
    		sum = sum.add(mA[i]).mod(n);
    		esum = esum.multiply(emA[i]).mod(nsqr);
    	}
    	UIController.getInstance().out("", 0);
    	UIController.getInstance().out("original sum: " + sum.toString(), 0);
    	UIController.getInstance().out("decrypted sum: " + Decryption(esum).toString(), 0);  	
    	
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
        UIController.getInstance().out("Pallier Key generation @ " + ((Integer) bitLength).toString() + "-bits", 0);
        UIController.getInstance().out("  p      : " + p.toString(), 0);
        UIController.getInstance().out("  q      : " + q.toString(), 0);
        UIController.getInstance().out("  n      : " + n.toString(), 0);
        UIController.getInstance().out("  n^2    : " + nsqr.toString(), 0);
        UIController.getInstance().out("  lambda : " + lambda.toString(), 0);
    }

    /**
     * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function explicitly requires random input r to help with encryption.
     * @param m plaintext as a BigInteger
     * @param r random plaintext to help with encryption
     * @return ciphertext as a BigInteger
     */
    /*public BigInteger Encryption(BigInteger m, BigInteger r) {
        return g.modPow(m, nsqr).multiply(r.modPow(n, nsqr)).mod(nsqr);
    }*/

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
        BigInteger u = g.modPow(lambda, nsqr).subtract(BigInteger.ONE).divide(n).modInverse(n);
        return c.modPow(lambda, nsqr).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }

}