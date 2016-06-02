package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BallotController;
import controller.UIController;
import model.Candidates;
import utils.Config;

public class BallotPanel extends JPanel {
	
	private JButton btnContinue;
	private JLabel heading;
	
	public JCheckBox [] votingButton;
	
	public BallotPanel () {
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("BallotPanel");

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize (new Dimension (Config.getWidth()-30, Config.getPanelHeight()));
		
		// Set up heading and colors
		JPanel headingHolder = new JPanel(new GridBagLayout());
		heading = new JLabel ("Select " + Config.getREQUIRED_CANDIDATES()+ " candidates");
		heading.setSize(new Dimension(800, 40));
		heading.setFont (new Font ("Courier", Font.BOLD, Config.getFontSize()));
		headingHolder.add (heading);
		add(headingHolder);

		JPanel ballotBoxPanel = new JPanel();
		
		String[] n = Candidates.getCArray();
		votingButton = new JCheckBox[n.length];
		for(int i = 0; i <n.length; i++) {
			votingButton[i] = new JCheckBox(n[i]);
			votingButton[i].addActionListener(new VotingListener(i));
			ballotBoxPanel.add(votingButton[i]);
		}
		
		btnContinue = new JButton("Place Vote");
		btnContinue.setPreferredSize(new Dimension(140, 36));
		btnContinue.setPreferredSize(new Dimension(140, 36));
		
		//btnContinue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
		
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				BallotController.getInstance().performVotingProcess();
			}
		});
		ballotBoxPanel.add(btnContinue, BorderLayout.EAST);
		add(ballotBoxPanel, BorderLayout.WEST);
		
	}
	
	// ***************************************************************
	// The listener for the CheckBoxes
	// ***************************************************************
	public class VotingListener implements ActionListener
	{		
		private int index;		
		public VotingListener (int i) {
			this.index = i;
		}		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JCheckBox button = (JCheckBox) arg0.getSource();
			if (BallotController.getInstance().getSelectionCount() == (Config.getREQUIRED_CANDIDATES()-1)) {
				UIController.getInstance().disableVoting();				
			};
			BallotController.getInstance().updateCandidateString(this.index);
		}
	}
	
	

}
