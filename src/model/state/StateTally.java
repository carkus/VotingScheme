package model.state;

import controller.SystemController;
import controller.TallyController;
import controller.UIController;

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
		UIController.getInstance().getTallyPanel().setVisible(true);
	}

	@Override
	public void endAction(SystemController s) {
		UIController.getInstance().getTallyPanel().setVisible(false);		
	}

	@Override
	public void updateAction(SystemController s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Bulletin Board - Tally of votes";
	}

	@Override
	public String getInstructions() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("This will act as the Bulletin Board for the Voting System.\n");
		str.append("Click the TALLY votes button to see a result of the election.\n\n");
		str.append("The process uses Paillier to decrypt the sum of the values.\n");
		return str.toString();
	}	
	

}
