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


}