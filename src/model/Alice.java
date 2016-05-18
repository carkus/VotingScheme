package model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import utils.Config;

public class Alice {

	private BigInteger bobPublicKey;
	private BigInteger bobBase;
	private BigInteger bobPrime;
	
	public Alice () { }
	
	public BigInteger[] doEncryption(String M) {
		BigInteger[] C1_C2 = new BigInteger[2];
		Random sc = new SecureRandom();
		BigInteger r = new BigInteger(Config.getBitSize(), sc);
		BigInteger X = new BigInteger(M);
		C1_C2[0] = getBobBase().modPow(r, getBobPrime());
		C1_C2[1] = X.multiply( bobPublicKey.modPow(r, getBobPrime()) ).mod(getBobPrime());
		return C1_C2;
	}

	public BigInteger getBobPublicKey() {
		return bobPublicKey;
	}

	public void setBobPublicKey(BigInteger bobPublicKey) {
		this.bobPublicKey = bobPublicKey;
	}

	public BigInteger getBobBase() {
		return bobBase;
	}

	public void setBobBase(BigInteger bobGenerator) {
		this.bobBase = bobGenerator;
	}

	public BigInteger getBobPrime() {
		return bobPrime;
	}

	public void setBobPrime(BigInteger bobPrime) {
		this.bobPrime = bobPrime;
	}

}
