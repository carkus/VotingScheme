package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SystemController;
import model.state.StateVoting;
import utils.Config;

public class BallotPanel extends JPanel {
	

	private JTextField regOutput = new JTextField();
	
	public BallotPanel() {
		super();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize(new Dimension(Config.getWidth()-30, 90));
		//this.setBackground(Color.RED);
		
		regOutput = new JTextField();	
		regOutput.setPreferredSize(new Dimension(500, 20));
		regOutput.setEditable(false);
		regOutput.setBorder(BorderFactory.createEmptyBorder());
		regOutput.setForeground(Color.RED);
		regOutput.setFont(new Font("Sans-serif", Font.BOLD, 16));
		
		add(regOutput);
		
	}
	
	private void checkRegistrationServer(String[] s) {
		//send to ElGamal for confirmation
		regOutput.setText("");
		if(!SystemController.getInstance().verifyVoterWithElGamal(s)){
			regOutput.setText("Registration Unsuccesful");
			return;
		}
		SystemController.getInstance().endAction();
		SystemController.getInstance().setAppState(StateVoting.getInstance());
	}
	
	

}
