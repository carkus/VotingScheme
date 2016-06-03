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
			//votingButton[i].addActionListener(new VotingListener(i));
			ballotBoxPanel.add(votingButton[i]);
		}
		
		btnContinue = new JButton("SUBMIT Vote");
		btnContinue.setEnabled(false);
		btnContinue.setPreferredSize(new Dimension(200, 36));
		
		//btnContinue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
		
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
		
		ballotBoxPanel.add(btnContinue, BorderLayout.EAST);
		ballotBoxPanel.add(btnReset, BorderLayout.EAST);
		add(ballotBoxPanel, BorderLayout.WEST);
		
	}
	

}
