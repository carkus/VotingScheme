package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import controller.SystemController;
import utils.Config;
import controller.UIController;

public class MainPanel extends JPanel {

	private SystemController systemController;
	private UIController uiController;
	
	private String title;
	
	public MainPanel () {
		super();
		systemController = SystemController.getInstance();
		uiController = UIController.getInstance();
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("MainPanel");
		
		setStateTitle(systemController.getAppState().getTitle().toString());
		
		JPanel pnContainer = new JPanel(new GridLayout(4, 1));
		pnContainer.setPreferredSize(new Dimension(Config.getWidth()-30, Config.getHeight()-410));
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		buildSubPanels();

	}

	public String getStateTitle() {		
		return systemController.getAppState().getTitle().toString();
	}
	
	public void setStateTitle(String s) {		
		title = s;
		TitledBorder titled = new TitledBorder(title);    	
    	this.setBorder(titled); 		
	}
	
	public void buildSubPanels() {
		RegistrationPanel regPanel = new RegistrationPanel();
		regPanel.setVisible(false);
		uiController.setRegistrationPanel(regPanel);
		this.add(regPanel);		

		BallotPanel ballotPanel = new BallotPanel();
		ballotPanel.setVisible(false);
		uiController.setBallotPanel(ballotPanel);
		this.add(ballotPanel);	
		
		ReviewPanel reviewPanel = new ReviewPanel();
		reviewPanel.setVisible(false);
		uiController.setReviewPanel(reviewPanel);
		this.add(reviewPanel);	

		TallyPanel tallyPanel = new TallyPanel();
		tallyPanel.setVisible(false);
		uiController.setTallyPanel(tallyPanel);
		this.add(tallyPanel);			
	}

	
}
