package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import controller.VotingController;
import model.algorithm.ElGamal;
import controller.UIController;

public class KeysPanel extends JPanel {

	private VotingController votingController;
	private UIController uiController;
	
	public KeysPanel () {
		this.setLayout(new GridBagLayout());
		votingController = VotingController.getInstance();
		uiController = UIController.getInstance();
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("KeysPanel");	    
		// Rebuild all components
	    this.setBorder(BorderFactory.createTitledBorder("El Gamal"));
	    this.setSize(new Dimension(400, 200));
	    this.setBackground(Color.WHITE);
	    
	    // Create labels
	    JLabel privateKeysLabel = new JLabel("Bob");
	    JLabel publicKeysLabel = new JLabel("Alice");	
	    
	    ArrayList <ElGamal> eG = new ArrayList <ElGamal>(votingController.getElGamal());
		
		for (int i=0; i<eG.size(); i++) {
		    // Instantiate text fields
		    JTextField textField1 = new JTextField(4);
		    textField1.setText(eG.get(i).getBobPrivateKey());
		    System.out.println("PRIVATE: " + i + " : " + eG.get(i).getBobPrivateKey());
		    
		    JTextField textField2 = new JTextField(4);
		    textField2.setText(eG.get(i).getBobPublicKey());	
		    System.out.println("PUBLIC: " + i + " : " + eG.get(i).getBobPublicKey());
		}
	    
	    
	    JLabel xLabel = new JLabel("Private Key");
	    JLabel kLabel = new JLabel("k");
	    JLabel pLabel = new JLabel("p");
	    JLabel gLabel = new JLabel("g");
	    JLabel rLabel = new JLabel("r");
	    

	    
	    /*JTextField textField3 = new JTextField(4);
	    textField3.setText(eG.getP());
	    
	    JTextField textField4 = new JTextField(4);
	    textField4.setText(eG.getG());
	    
	    JTextField textField5 = new JTextField(4);
	    textField5.setText(eG.getR());*/
	    
	    // Place the components into the ElGamal panel
	    

	    /*uiController.setComponent(this, privateKeysLabel, 0, 0, 13, 1, 1, 2, 2, new Insets(5,15,5,15));
	    uiController.setComponent(this, xLabel, 0, 1, 1, 1, 0, 0, 0, new Insets(0,15,5,0));
	    
	    
	    uiController.setComponent(this, textField1, 1, 1, 3, 1, 0, 0, 0, new Insets(0,1,5,10));
	    uiController.setComponent(this, kLabel, 5, 1, 1, 1, 0, 0, 0, new Insets(0,5,5,0));
	    uiController.setComponent(this, textField2, 6, 1, 3, 1, 0, 0, 0, new Insets(0,1,5,10));
	    
	    uiController.setComponent(this, new JSeparator(), 0, 2, 13, 1, 1, 2, 2, new Insets(0,7,0,7));
	    
	    uiController.setComponent(this, publicKeysLabel, 0, 3, 13, 1, 1, 2, 2, new Insets(5,15,5,15));
	    uiController.setComponent(this, pLabel, 0, 4, 1, 1, 0, 0, 0, new Insets(0,15,5,0));*/
	    
	    /*uiController.setComponent(this, textField3, 1, 4, 3, 1, 0, 0, 0, new Insets(0,1,5,10));
	    uiController.setComponent(this, gLabel, 5, 4, 1, 1, 0, 0, 0, new Insets(0,5,5,0));
	    uiController.setComponent(this, textField4, 6, 4, 3, 1, 0, 0, 0, new Insets(0,1,5,10));
	    uiController.setComponent(this, rLabel, 9, 4, 1, 1, 0, 0, 0, new Insets(0,5,5,0));
	    uiController.setComponent(this, textField5, 10, 4, 3, 1, 0, 0, 0, new Insets(0,1,5,10));*/
	
	    //uiController.setComponent(this, this, 0, 1, 4, 3, 1, 1, 1, new Insets(10,5,0,5));
	    //uiController.setComponent(this, newKeyButton, 0, 4, 1, 1, 0, 0, 0, new Insets(5,10,10,15));
	}	

	
}
