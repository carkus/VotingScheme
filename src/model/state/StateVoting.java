package model.state;

import controller.SystemController;
import controller.UIController;

public class StateVoting implements IAppState {

	private StateVoting() { }

	private static StateVoting instance;

	public static StateVoting getInstance() {
		if (instance == null) {
			instance = new StateVoting();
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
		str.append("Select 3 candidates you wish to vote for.\n");
		str.append("Incomplete votes will NOT be counted. But WILL be allowed, in an attempt to minimise coersion.\n");
		str.append("After validation, the votes will be encrypted and tallied using (additive homomorphic) Paillier.");
		return str.toString();
	}	

}