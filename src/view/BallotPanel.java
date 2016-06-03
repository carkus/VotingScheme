package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BallotController;
import controller.SystemController;
import controller.UIController;
import model.Candidates;
import model.state.StateReview;
import utils.Config;

public class BallotPanel extends JPanel {
	
	private JButton btnContinue;
	private JButton btnReset;
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
		
		btnContinue = new JButton("REVIEW Vote");
		btnContinue.setEnabled(false);
		btnContinue.setPreferredSize(new Dimension(200, 36));

		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SystemController.getInstance().changeState(StateReview.getInstance());
			}
		});
		
		btnReset = new JButton("START AGAIN");
		btnReset.setEnabled(true);
		btnReset.setPreferredSize(new Dimension(140, 36));

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetBallot();
			}
		});
		ballotBoxPanel.add(btnContinue, BorderLayout.EAST);
		ballotBoxPanel.add(btnReset, BorderLayout.EAST);
		add(ballotBoxPanel, BorderLayout.WEST);
		
	}
	
	public void resetBallot() {
		for(int i = 0; i <votingButton.length; i++) {
			votingButton[i].setEnabled(true);
			votingButton[i].setSelected(false);			
		}
		BallotController.getInstance().resetCandidateString();
		BallotController.getInstance().setPref(Config.getREQUIRED_CANDIDATES());
		heading.setText("Select " + Config.getREQUIRED_CANDIDATES()+ " candidates");
		btnContinue.setEnabled(false);
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
			BallotController.getInstance().setVote(votingButton, this.index);
			if (BallotController.getInstance().checkSelectionCount(votingButton) == (Config.getREQUIRED_CANDIDATES())) {				
				UIController.getInstance().disableVoting();
				UIController.getInstance().getBallotPanel().btnContinue.setEnabled(true);
				heading.setText("You may REVIEW your vote or START AGAIN");
			} else {
				heading.setText("Select " + BallotController.getInstance().getPref() + " candidates");
				UIController.getInstance().getBallotPanel().btnContinue.setEnabled(false);
				//UIController.getInstance().out("SEND  " + egEncryption[0] + ", " + egEncryption[1], 1);
			}
		}
	}
	
	

}
