package utils;

public class Config {

    private static final int BIT_SIZE = 64;
	public static int getBitSize() {
		return BIT_SIZE;
	}
	
	private static final int PRIME_LOW = 1;
	public static int getPrimeLow() {
		return PRIME_LOW;
	}
	
	private static final int MAX_HIGH = 20000;
	private static final int PRIME_HIGH = 20000;
	
	public static int getPrimeHigh() {
		return PRIME_HIGH;
	}
	public static int getMaxHigh() {
		return MAX_HIGH;
	}


}
