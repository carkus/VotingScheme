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
		// TODO Auto-generated method stub
		
		//UIController.getInstance().out("Start Reg...");
		
	}

	@Override
	public void endAction(SystemController s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(SystemController s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Registration";
	}	


}