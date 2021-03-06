package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.SystemController;
import controller.UIController;
import utils.Config;

public class InstructionPanel extends JPanel {
	
	private UIController uiController;
	private SystemController systemController;
	
	private JLabel heading;
	private JPanel wrapper = new JPanel(new GridBagLayout());
	private JTextArea iText = new JTextArea();
	
	public InstructionPanel () {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		systemController = SystemController.getInstance();
		uiController = UIController.getInstance();		
		uiController.setInstructionPanel(this);
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("InstructionPanel");
		
		// Set up heading and colors	
		iText.setEditable(false);
		iText.setForeground(Color.BLACK);
		iText.setFont(new Font("Sans-serif", Font.BOLD, 12));
		iText.setText("");
		iText.setLineWrap(true);
		iText.setWrapStyleWord(true);
		iText.setMargin(new Insets(5,5,5,5));
		iText.setBackground(new Color(239, 239, 239));
		
	    TitledBorder titled = new TitledBorder("Instructions");
	    
	    this.setBorder(titled);
		this.add(iText);
		this.setPreferredSize (new Dimension(Config.getWidth()-10, 140));
		
	}

	public void setInnerText(String text) {
		iText.setText(text);
	}

}
