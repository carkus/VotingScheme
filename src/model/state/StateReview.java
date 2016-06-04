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
		return "Review your vote";
	}

	@Override
	public String getInstructions() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("A vote must first be verified before it can be cast.\n");
		str.append("Please ensure the details below match your intended vote.\n\n");
		str.append("You may click START AGAIN if you wish to cancel this vote. No record will be kept.\n");
		return str.toString();
	}	

}