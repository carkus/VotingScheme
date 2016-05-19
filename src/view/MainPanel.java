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
	
	public MainPanel () {
		super();
		systemController = SystemController.getInstance();
		uiController = UIController.getInstance();
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("MainPanel");
		
		String title = getStateTitle();		
    	TitledBorder titled = new TitledBorder(title);
    	
    	this.setBorder(titled);   		
		
		JPanel pnContainer = new JPanel(new GridLayout(4, 1));
		pnContainer.setPreferredSize(new Dimension(Config.getWidth()-30, Config.getHeight()-410));
		pnContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		this.add(pnContainer);
		
	}
	
	private void makeGUI() {
		JTabbedPane jtp = new JTabbedPane();
		/*jtp.addTab("Cities", new CitiesPanel());
		jtp.addTab("Colors", new ColorsPanel());
		jtp.addTab("Flavors", new FlavorsPanel());*/
		add(jtp);
	}
	
	public String getStateTitle() {		
		return systemController.getAppState().getTitle().toString();
	}

	
}
