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

import controller.SystemController;
import model.algorithm.ElGamal;
import controller.UIController;

public class TallyPanel extends JPanel {

	private SystemController systemController;
	private UIController uiController;
	
	public TallyPanel () {
		this.setLayout(new GridBagLayout());
		systemController = SystemController.getInstance();
		uiController = UIController.getInstance();
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("TallyPanel");

	    // Rebuild all components
	    this.setBorder(BorderFactory.createTitledBorder("Results"));
	    
	    // Create labels
	    JLabel lastVoteLabel = new JLabel("Last Vote");
	    JLabel tallyLabel = new JLabel("Tally");
	    
	    //ArrayList <ElGamal> eG = new ArrayList <ElGamal>(systemController.getElGamal());
		
		/*for (int i=0; i<eG.size(); i++) {
		    // Instantiate text fields
		    JTextField textField1 = new JTextField(4);
		    textField1.setText(eG.get(i).getBobPrivateKey());
		    System.out.println("TALLY PRIVATE: " + i + " : " + eG.get(i).getBobPrivateKey());
		    
		    JTextField textField2 = new JTextField(4);
		    textField2.setText(eG.get(i).getBobPublicKey());	
		    System.out.println("TALLY PUBLIC: " + i + " : " + eG.get(i).getBobPublicKey());
		}*/	    

	    /*uiController.setComponent(this, lastVoteLabel, 0, 0, 13, 1, 1, 2, 2, new Insets(5,15,5,15));
	    uiController.setComponent(this, tallyLabel, 0, 10, 13, 1, 1, 2, 2, new Insets(5,15,5,15));*/

	}	

	
}
