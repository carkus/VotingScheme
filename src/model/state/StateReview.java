package model.state;

import controller.SystemController;
import controller.UIController;

public class StateReview implements IAppState {

	private StateReview() { }

	private static StateReview instance;

	public static StateReview getInstance() {
		if (instance == null) {
			instance = new StateReview();
		}
		return instance;
	}

	@Override
	public void startAction(SystemController s) {
		UIController.getInstance().getReviewPanel().setVisible(true);	
	}

	@Override
	public void endAction(SystemController s) {
		UIController.getInstance().getReviewPanel().setVisible(false);	
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
		str.append("A vote must first be verified by the \n");
		str.append("Select 3 candidates you wish to vote for.\n");
		str.append("Incomplete votes will NOT be counted. But WILL be allowed, in an attempt to minimise coersion.\n");
		str.append("After validation, the votes will be encrypted and tallied using (additive homomorphic) Paillier.");
		return str.toString();
	}	

}