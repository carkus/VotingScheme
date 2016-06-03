package utils;

public class Config {

	//Frame size
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	
	//Algorithm parameters
    private static final int PRIME_CERTAINTY = 64;
    private static final int ELGAMAL_BIT_SIZE = 24;
    private static final int PAILLIER_BIT_SIZE = 128;
    private static final int MAX_HIGH = 20000;
    private static final int PRIME_HIGH = 20000;
    private static final int PRIME_LOW = 1;
    
    //Voting rules
    private static boolean ALLOW_INCOMPLETE = false; 
    private static int REQUIRED_CANDIDATES = 3;
	private static int VOTERS = 10;
	
	//UI
	private static final int PANEL_HEIGHT = 80;
	private static final int FONT_SIZE = 20;
	
    //ACCESSORS
    public static int getPrimeCertainty() {
		return PRIME_CERTAINTY;
	}	
	public static int getPrimeLow() {
		return PRIME_LOW;
	}
	public static int getPrimeHigh() {
		return PRIME_HIGH;
	}
	public static int getMaxHigh() {
		return MAX_HIGH;
	}
	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() {
		return HEIGHT;
	}
	public static int getPaillierBitSize() {
		return PAILLIER_BIT_SIZE;
	}
	public static int getElgamalBitSize() {
		return ELGAMAL_BIT_SIZE;
	}
	public static boolean getALLOW_INCOMPLETE() {
		return ALLOW_INCOMPLETE;
	}
	public static void setALLOW_INCOMPLETE(boolean ai) {
		ALLOW_INCOMPLETE = ai;
	}
	public static int getREQUIRED_CANDIDATES() {
		return REQUIRED_CANDIDATES;
	}
	public static void setREQUIRED_CANDIDATES(int rEQUIRED_CANDIDATES) {
		REQUIRED_CANDIDATES = rEQUIRED_CANDIDATES;
	}
	public static int getVOTERS() {
		return VOTERS;
	}
	public static void setVOTERS(int vOTERS) {
		VOTERS = vOTERS;
	}
	public static int getFontSize() {
		return FONT_SIZE;
	}
	public static int getPanelHeight() {
		return PANEL_HEIGHT;
	}


}
