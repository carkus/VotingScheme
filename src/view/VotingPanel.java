package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalCheckBoxIcon;

import controller.SystemController;
import controller.UIController;
import model.Candidates;
import utils.Config;

public class VotingPanel extends JPanel {
	
	OutputPanel outputPanel = new OutputPanel();
	InstructionPanel instructionPanel = new InstructionPanel();
	MainPanel mainPanel = new MainPanel();

	public VotingPanel() {
		
		super();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(Config.getWidth(), 600));

		JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		contentPanel.add(instructionPanel);
		contentPanel.add(mainPanel);
		
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(outputPanel, BorderLayout.SOUTH);
	    
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
			//systemController.updateCandidateString(this.index);
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

	public OutputPanel getOutputPanel() {
		return outputPanel;
	}
	
	public void setOutputPanel(OutputPanel outputPanel) {
		this.outputPanel = outputPanel;
	}
}

