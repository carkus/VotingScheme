package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import controller.BallotController;
import controller.SystemController;
import controller.TallyController;
import model.Candidates;
import model.algorithm.ElGamal;
import model.state.StateRegistration;
import model.state.StateTally;
import utils.Config;
import utils.Helpers;
import view.BallotPanel.VotingListener;
import controller.UIController;

public class TallyPanel extends JPanel {
	
	private JButton btnTally;
	private JButton btnContinue;
	private JLabel heading;
	private JTextField [] tallyTextField;

	private JLabel [] tallyTextLabel;
	
	public TallyPanel () {
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("TallyPanel");

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize (new Dimension (Config.getWidth()-30, Config.getPanelHeight()));
		
		// Set up heading and colors
		JPanel headingHolder = new JPanel(new GridBagLayout());
		heading = new JLabel ("The Winners and the Losers");
		heading.setSize(new Dimension(800, 40));
		heading.setFont (new Font ("Courier", Font.BOLD, Config.getFontSize()));
		headingHolder.add (heading);
		add(headingHolder);

		JPanel ballotBoxPanel = new JPanel(new FlowLayout());
		
		String[] n = Candidates.getCArray();
		tallyTextLabel = new JLabel[n.length];
		tallyTextField = new JTextField[n.length];
		
		for(int i = 0; i <n.length; i++) {
			tallyTextLabel[i] = new JLabel(n[i]);			
			tallyTextField[i] = new JTextField("");
			tallyTextField[i].setPreferredSize(new Dimension(30, 20));
			ballotBoxPanel.add(tallyTextLabel[i]);
			ballotBoxPanel.add(tallyTextField[i]);
		}
		
		btnTally = new JButton("Tally Votes");
		btnTally.setPreferredSize(new Dimension(100, 36));
		btnTally.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TallyController.getInstance().doTallyProcess();
				TallyController.getInstance().setTallyValues();
				btnTally.setEnabled(false);
				btnContinue.setEnabled(true);
			}
		});
		btnContinue = new JButton("Restart");
		btnContinue.setPreferredSize(new Dimension(100, 36));
		btnContinue.setEnabled(false);
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnTally.setEnabled(true);
				btnContinue.setEnabled(false);
				Helpers.getInstance().resetApplication();
				SystemController.getInstance().changeState(StateRegistration.getInstance());
			}
		});
		ballotBoxPanel.add(btnTally, BorderLayout.EAST);
		ballotBoxPanel.add(btnContinue, BorderLayout.EAST);
		add(ballotBoxPanel, BorderLayout.WEST);
		
	}

	public JTextField[] getTallyTextField() {
		return tallyTextField;
	}
	
	public void setTallyTextField(JTextField[] tallyTextField) {
		this.tallyTextField = tallyTextField;
	}

}
