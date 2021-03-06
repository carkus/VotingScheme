package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.RegistrationController;
import controller.SystemController;
import controller.UIController;
import model.Voter;
import model.state.StateBallot;
import utils.Config;


public class RegistrationPanel extends JPanel{

	private ArrayList<Voter> dbList = new ArrayList<Voter>();
	private String[] regValues = new String[]{"9","3","7"};//dummy data to set up array 
	
	private JComboBox<String> cbFirstName = new JComboBox<String>();
	private JComboBox<String> cbLastName = new JComboBox<String>();
	private JComboBox<String> cbSecret = new JComboBox<String>();
	private JButton btnContinue = new JButton();
	private JTextField regOutput = new JTextField();
	
	public JTextField getRegOutput() {
		return regOutput;
	}

	public void setRegOutput(JTextField regOutput) {
		this.regOutput = regOutput;
	}

	public RegistrationPanel() {
		super();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setPreferredSize (new Dimension (Config.getWidth()-30, Config.getPanelHeight()));
		//this.setBackground(Color.RED);
				
		dbList = SystemController.getInstance().getVoterList();		
		
		Collections.shuffle(dbList);
		
		for (int i=0; i<dbList.size(); i++) {
			cbFirstName.addItem(dbList.get(i).getFirstName());
			cbLastName.addItem(dbList.get(i).getLastName());
			cbSecret.addItem(dbList.get(i).getSecret());
		}
		
		cbFirstName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				regValues[0] = ((Integer) cbFirstName.getSelectedIndex()).toString();
			}
		});			
		cbLastName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				regValues[1] = ((Integer) cbLastName.getSelectedIndex()).toString();
			}
		});				
		cbSecret.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				regValues[2] = ((Integer) cbSecret.getSelectedIndex()).toString();
			}
		});				
		
		cbFirstName.setSelectedIndex(new Random().nextInt(cbFirstName.getItemCount()));
		cbLastName.setSelectedIndex(new Random().nextInt(cbLastName.getItemCount()));
		cbSecret.setSelectedIndex(new Random().nextInt(cbSecret.getItemCount()));
		
		cbFirstName.setPreferredSize(new Dimension((Config.getWidth()/4), 30));
		cbLastName.setPreferredSize(new Dimension((Config.getWidth()/4), 30));
		cbSecret.setPreferredSize(new Dimension((Config.getWidth()/4), 30));
		
		btnContinue = new JButton("Register");
		btnContinue.setPreferredSize(new Dimension((Config.getWidth()/4)-60, 36));
		btnContinue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrationController.getInstance().checkRegistrationServer(regValues);
			}
		});
		
		regOutput = new JTextField();	
		regOutput.setPreferredSize(new Dimension(500, 20));
		regOutput.setEditable(false);
		regOutput.setBorder(BorderFactory.createEmptyBorder());
		regOutput.setForeground(Color.RED);
		regOutput.setFont(new Font("Sans-serif", Font.BOLD, 16));
		
		add(cbFirstName);
		add(cbLastName);
		add(cbSecret);
		add(btnContinue);
		add(regOutput);
		
	}
	
}
