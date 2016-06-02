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
import javax.swing.border.EmptyBorder;


//Voting rules
/*private static boolean ALLOW_INCOMPLETE = true; 
private static boolean SIMULATE_BIAS = true; 
private static int REQUIRED_CANDIDATES = 3;
private static int VOTERS = 1000;*/

public class OptionFrame extends JFrame {
	
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
		this.getContentPane().setPreferredSize(new Dimension(300, 100));

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

		
		JButton btOK = new JButton("OK");
		btOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JButton btCancel = new JButton("Cancel");
		btCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doCloseFrame();
			}
		});
		
		/*optionPanel.add(lbSelect);
		optionPanel.add(cbMapList);*/
		optionPanel.add(btOK);
		optionPanel.add(btCancel);
		
		this.add(optionPanel);
		this.pack();
	}
	
	private void doCloseFrame(){
		this.dispose();
	}
}
