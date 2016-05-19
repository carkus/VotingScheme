package model.state;

import controller.SystemController;

public class StateTally implements IAppState {
	
	private StateTally() { }

	private static StateTally instance;

	public static StateTally getInstance() {
		if (instance == null) {
			instance = new StateTally();
		}
		return instance;
	}

	@Override
	public void startAction(SystemController s) {
		// TODO Auto-generated method stub
		
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
		return "Tally of votes";
	}	
	

}
