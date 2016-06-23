package model;

public class Voter implements IVoter {	
	
	private String firstName;
	private String lastName;
	private String secret;
	private Boolean hasVoted = false;
	
	@Override
	public void setFirstName(String s) {
		firstName = s;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setLastName(String s) {
		lastName = s;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setSecret(String s) {
		secret = s;
	}

	@Override
	public String getSecret() {
		return secret;
	}

	@Override
	public void setHasVoted(Boolean b) {
		hasVoted = b;
	}

	@Override
	public Boolean getHasVoted() {
		return hasVoted;
	}

}
