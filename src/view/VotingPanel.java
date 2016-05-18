package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalCheckBoxIcon;

import controller.VotingController;
import controller.UIController;
import model.Candidates;

public class VotingPanel extends JPanel {
	
	private final int WIDTH = 350, HEIGHT = 90, FONT_SIZE = 20;
	private final int NUM_COLORS = 5;
	private Color [] color = new Color[NUM_COLORS];
	private JLabel heading;
	private JCheckBox [] votingButton;
	
	private VotingController systemController;
	
	public VotingPanel () {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		systemController = VotingController.getInstance();
		buildPanel();
	}

	private void buildPanel() {
		
		System.out.println("VotingPanel");
		
		// Set up heading and colors
		JPanel headingHolder = new JPanel(new GridBagLayout());
		heading = new JLabel ("Select 3 candidates");
		heading.setSize(new Dimension(800, 40));
		heading.setFont (new Font ("Courier", Font.BOLD, FONT_SIZE));
		headingHolder.add (heading);
		add(headingHolder);

		JPanel buttonPanel = new JPanel();
		
		String[] n = Candidates.getCArray();
		votingButton = new JCheckBox[n.length];
		for(int i = 0; i <n.length; i++) {
			votingButton[i] = new JCheckBox(n[i]);
			HighlightOnSelectIcon icon = new HighlightOnSelectIcon();
			votingButton[i].setIcon(icon);
			votingButton[i].addActionListener(new VotingListener(i));
			buttonPanel.add(votingButton[i]);
		}
		
		setBackground (Color.white);
		setPreferredSize (new Dimension (WIDTH, HEIGHT));		
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	// ***************************************************************
	// The listener for the CheckBoxes
	// ***************************************************************
	public class VotingListener implements ActionListener
	{		
		private int index;		
		public VotingListener (int i) {
			this.index = i;
		}		
		public void actionPerformed (ActionEvent e) {
			JCheckBox button = (JCheckBox) e.getSource();
			System.out.println("click: " + this.index);
			systemController.updateCandidateString(this.index);
		}
	}
	
	// ***************************************************************
	// Adds the Color tick representing preference for the CheckBoxes
	// ***************************************************************
	public class HighlightOnSelectIcon extends MetalCheckBoxIcon {

	    private Icon wrappedIcon = UIManager.getIcon("CheckBox.icon");

	    @Override
	    protected void drawCheck(Component c, Graphics g, int x, int y) {
	    	g.setColor(Color.BLACK);
	      	super.drawCheck(c, g, x, y);
	    }

	    @Override
	    public void paintIcon(Component c, Graphics g, int x, int y) {
	    	wrappedIcon.paintIcon(c, g, x, y);
	      	JCheckBox cb = (JCheckBox) c;
	      	/*if (cb.isSelected()) {
	      		PrefColor col = (PrefColor) PrefColor.values()[uiController.getPrefCount()];
	    		this.color = new Color(col.getRed(),col.getGreen(),col.getBlue());
				g.setColor(new Color(col.getRed(),col.getGreen(),col.getBlue()););
				uiController.getPrefCount();
				g.fillRect(x, y, getIconWidth(), getIconHeight());
				drawCheck(c, g, x, y);
	      	}*/	      	
	    }
	}

}

