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

}