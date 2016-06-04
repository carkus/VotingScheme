package model.state;

import controller.SystemController;
import controller.UIController;

public class StateBallot implements IAppState {

	private StateBallot() { }

	private static StateBallot instance;

	public static StateBallot getInstance() {
		if (instance == null) {
			instance = new StateBallot();
		}
		return instance;
	}

	@Override
	public void startAction(SystemController s) {
		UIController.getInstance().getBallotPanel().setVisible(true);	
	}

	@Override
	public void endAction(SystemController s) {
		UIController.getInstance().getBallotPanel().setVisible(false);	
	}

	@Override
	public void updateAction(SystemController s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Voting";
	}

	@Override
	public String getInstructions() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("Registration successful. You can now place your vote.\n");
		str.append("Select 3 candidates in DESCENDING order of preference.\n");
		str.append("If you make a mistake, you may click START AGIN to clear your vote.\n\n");
		str.append("Incomplete votes will NOT be allowed for this ballot.\n");
		str.append("After validation, the votes will be encrypted and tallied using (additive homomorphic) Paillier.");
		return str.toString();
	}	

}