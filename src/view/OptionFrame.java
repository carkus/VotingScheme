package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utils.Config;

public class OptionFrame extends JFrame {
	
	JLabel lblCandidates;
	JTextField tfCandidates;
	JLabel lblVoters;
	JTextField tfVoters;
	JLabel lblElgamalBit;
	JTextField tfElgamalBit;
	JLabel lblPailBit;
	JTextField tfPailBit;
	
	public OptionFrame() {
		super();
		buildFrame();
		buildUI();		
	}
	
	/**
	 * Adding and configuring properties of the frame
	 */
	private void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// adjust size using Dimension.
		this.getContentPane().setPreferredSize(new Dimension(300, 200));

		this.setResizable(false);
		this.setVisible(false);
		
		//Position Options in center of Screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, 
				dim.height/2-this.getSize().height);
	}

	/**
	 * Creating the option UI
	 */
	private void buildUI() {
		JPanel optionPanel = new JPanel(new GridLayout(0, 2));
		int margin = 10;
		optionPanel.setBorder(new EmptyBorder(margin, margin, margin, margin));		

		lblCandidates = new JLabel("Number of Vote Preferences:");
		tfCandidates = new JTextField("");
		lblVoters = new JLabel("Number of Voters:");
		tfVoters = new JTextField("");
		lblElgamalBit = new JLabel("ElGamal bit-size:");
		tfElgamalBit = new JTextField("");
		lblPailBit = new JLabel("Paillier bit-size:");
		tfPailBit = new JTextField("");
		
		JButton btOK = new JButton("OK");
		btOK.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Config.setREQUIRED_CANDIDATES(Integer.parseInt(tfCandidates.getText()));
				Config.setVOTERS(Integer.parseInt(tfVoters.getText()));
				Config.setELGAMAL_BIT_SIZE(Integer.parseInt(tfElgamalBit.getText()));
				Config.setPAILLIER_BIT_SIZE(Integer.parseInt(tfPailBit.getText()));	
				doCloseFrame();
			}
		});
		
		JButton btCancel = new JButton("Cancel");
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doCloseFrame();
			}
		});
		
		optionPanel.add(lblCandidates);
		optionPanel.add(tfCandidates);
		optionPanel.add(lblVoters);
		optionPanel.add(tfVoters);
		optionPanel.add(lblElgamalBit);
		optionPanel.add(tfElgamalBit);
		optionPanel.add(lblPailBit);
		optionPanel.add(tfPailBit);
		optionPanel.add(btOK);
		optionPanel.add(btCancel);
		
		this.add(optionPanel);
		this.pack();
	}
	
	private void doCloseFrame(){
		this.dispose();
	}
	
	public void setOptionValues(){
		tfCandidates.setText(String.valueOf(Config.getREQUIRED_CANDIDATES()));
		tfVoters.setText(String.valueOf(Config.getVOTERS()));
		tfElgamalBit.setText(String.valueOf(Config.getElgamalBitSize()));
		tfPailBit.setText(String.valueOf(Config.getPaillierBitSize()));
	}
}
