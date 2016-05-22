package model;

public interface IVoter {
	
	public void setFirstName(String s);
	
	public String getFirstName();

	public void setLastName(String s);
	
	public String getLastName();
	
	public void setSecret(String s);
	
	public String getSecret();
	
	public void setHasVoted(Boolean b);
	
	public Boolean getHasVoted();

}
