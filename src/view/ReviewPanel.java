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
import javax.swing.JTextField;

import controller.BallotController;
import controller.SystemController;
import controller.UIController;
import model.Candidates;
import model.state.StateReview;
import model.state.StateVoting;
import utils.Config;

public class ReviewPanel extends JPanel {
	
	private JButton btnContinue;
	private JButton btnReset;
	private JLabel heading;
	

	public JCheckBox [] votingButton;
	
	public ReviewPanel () {
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("ReviewPanel");

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize (new Dimension (Config.getWidth()-30, Config.getPanelHeight()));
		
		// Set up heading and colors
		JPanel headingHolder = new JPanel(new GridBagLayout());
		heading = new JLabel ("Review Your Vote");
		heading.setSize(new Dimension(800, 40));
		heading.setFont (new Font ("Courier", Font.BOLD, Config.getFontSize()));
		headingHolder.add (heading);
		add(headingHolder);

		JPanel reviewBoxPanel = new JPanel();
		
		btnContinue = new JButton("SUBMIT Vote");
		btnContinue.setPreferredSize(new Dimension(200, 36));

		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				BallotController.getInstance().performVotingProcess();
			}
		});
		
		btnReset = new JButton("START AGAIN");
		btnReset.setEnabled(true);
		btnReset.setPreferredSize(new Dimension(140, 36));

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				UIController.getInstance().getBallotPanel().resetBallot();
				SystemController.getInstance().changeState(StateVoting.getInstance());
			}
		});
		
		reviewBoxPanel.add(btnContinue, BorderLayout.EAST);
		reviewBoxPanel.add(btnReset, BorderLayout.EAST);
		add(reviewBoxPanel, BorderLayout.WEST);
		
	}
	
	public void writeSelection() {
		String[] n = Candidates.getCArray();		
		String voteString = Candidates.getInputString();
		StringBuilder str = new StringBuilder();
		int cpref = Config.getREQUIRED_CANDIDATES();
		while (cpref > 0) {
			int i = voteString.indexOf(String.valueOf(cpref));			
			if (i != -1) str.append("  " + n[i] + ":" + cpref);
			cpref--;
		}
		heading.setText(str.toString());		
	}
	
	

}
