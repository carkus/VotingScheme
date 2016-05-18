package controller;

public class Server implements IServer {
	
	private int serverID;

	//these need to be set to an encrypted Type
	private int eUID;
	private int eVote;
	private int ePrefID;
	
	public Server () {
		
	}

	@Override
	public String encrypt(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String cipherText) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
