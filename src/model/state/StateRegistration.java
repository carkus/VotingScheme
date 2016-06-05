package model.state;

import controller.SystemController;
import controller.UIController;

public class StateRegistration implements IAppState {

	private StateRegistration() { }

	private static StateRegistration instance;

	public static StateRegistration getInstance() {
		if (instance == null) {
			instance = new StateRegistration();
		}
		return instance;
	}

	@Override
	public void startAction(SystemController s) {
		UIController.getInstance().getRegistrationPanel().setVisible(true);		
	}

	@Override
	public void endAction(SystemController s) {
		UIController.getInstance().getRegistrationPanel().setVisible(false);
	}

	@Override
	public void updateAction(SystemController s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		return "Registration";
	}

	@Override
	public String getInstructions() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("Before you can vote, you need to register your eligibility with your identity.\n");
		str.append("Select your First and Last name, alongside the answer to your Secret Question, which you provided previously.\n");
		str.append("(see Appendix D in the report for working combinations)\n\n");
		str.append("The system will send the encrypted indexes of each item using ElGamal, and compare the results of decryption.\n");
		str.append("If you have previously voted, you will not be able to vote again.");
		return str.toString();
	}	


}