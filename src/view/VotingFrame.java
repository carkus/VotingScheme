package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import utils.Config;

public class VotingFrame extends JFrame {
	
	private MenuBarUI menuBar;
	private VotingPanel votingPanel;
	
	public VotingFrame() {
		super("e-Voting System");
		buildFrame();
		buildUI();
	}

	/**
	 * Adding and configuring properties of the frame
	 */
	private void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		c.setBackground(Color.WHITE);

		// adjust size using Dimension.
		c.setPreferredSize(new Dimension(Config.getWidth(), Config.getHeight()));
		
		// standard JFrame startup...
		this.setResizable(false);
		this.setVisible(false);
		
	}

	/**
	 * Creating the board panel and control panel
	 */
	private void buildUI() {
		
    	//MENUBAR
    	menuBar = new MenuBarUI();
    	this.setJMenuBar(menuBar);
    	
    	votingPanel = new VotingPanel();
		this.add(votingPanel, BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}

	public VotingPanel getVotingPanel() {
		return votingPanel;
	}
	
	public void setVotingPanel(VotingPanel votingPanel) {
		this.votingPanel = votingPanel;
	}
	
}
