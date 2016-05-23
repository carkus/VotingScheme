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
import model.Candidates;
import model.algorithm.ElGamal;
import view.BallotPanel.VotingListener;
import controller.UIController;

public class TallyPanel extends JPanel {

	private final int WIDTH = 770, HEIGHT = 90, FONT_SIZE = 20;
	
	private JButton btnContinue;
	private JLabel heading;
	private JTextField [] tallyTextField;

	private JLabel [] tallyTextLabel;
	
	public TallyPanel () {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize (new Dimension (WIDTH, HEIGHT));
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("TallyPanel");
		
		// Set up heading and colors
		JPanel headingHolder = new JPanel(new GridBagLayout());
		heading = new JLabel ("The Winners and the Losers");
		heading.setSize(new Dimension(800, 40));
		heading.setFont (new Font ("Courier", Font.BOLD, FONT_SIZE));
		headingHolder.add (heading);
		add(headingHolder);

		JPanel ballotBoxPanel = new JPanel(new FlowLayout());
		
		String[] n = Candidates.getCArray();
		tallyTextLabel = new JLabel[n.length];
		tallyTextField = new JTextField[n.length];
		for(int i = 0; i <n.length; i++) {
			
			tallyTextLabel[i] = new JLabel(n[i]);			
			tallyTextField[i] = new JTextField("");
			
			//votingButton[i].addActionListener(new VotingListener(i));
			ballotBoxPanel.add(tallyTextLabel[i]);
			ballotBoxPanel.add(tallyTextField[i]);
		}
		
		btnContinue = new JButton("Restart");
		btnContinue.setPreferredSize(new Dimension(140, 36));
		btnContinue.setPreferredSize(new Dimension(140, 36));
		
		//btnContinue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
		
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				//BallotController.getInstance().performVotingProcess();
			}
		});
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
