package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.SystemController;
import utils.Config;

public class InstructionPanel extends JPanel {
	
	private final int HEIGHT = 100;
	private final int FONT_SIZE = 20;
	
	private SystemController systemController;
	
	private JLabel heading;
	private JPanel wrapper = new JPanel(new GridBagLayout());
	
	public InstructionPanel () {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		systemController = SystemController.getInstance();
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("InstructionPanel");
		
		// Set up heading and colors
		JTextField iText = new JTextField();
		iText.setHorizontalAlignment(JTextField.CENTER);		
		iText.setEditable(false);
		iText.setForeground(Color.BLACK);
		iText.setFont(new Font("Sans-serif", Font.BOLD, 10));
		iText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
	    TitledBorder titled = new TitledBorder("Instructions");
	    this.setBorder(titled);

		this.add(iText);

		/*JPanel buttonPanel = new JPanel();
		
		String[] n = Candidates.getCArray();
		votingButton = new JCheckBox[n.length];
		for(int i = 0; i <n.length; i++) {
			votingButton[i] = new JCheckBox(n[i]);
			HighlightOnSelectIcon icon = new HighlightOnSelectIcon();
			votingButton[i].setIcon(icon);
			votingButton[i].addActionListener(new VotingListener(i));
			buttonPanel.add(votingButton[i]);
		}*/
		
		/*wrapper.add (heading);
		this.add(wrapper);
		this.setBackground (Color.RED);*/
		
		
		this.setPreferredSize (new Dimension(Config.getWidth()-10, HEIGHT));
		
	}

}
