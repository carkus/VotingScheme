package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorPanel extends JPanel
{
	private final int WIDTH = 350, HEIGHT = 100, FONT_SIZE = 20;
	private final int NUM_COLORS = 5;
	private Color [] color = new Color[NUM_COLORS];
	private JLabel heading;
	private JRadioButton [] colorButton= new JRadioButton[color.length];

	
	public ColorPanel ()
	{
		
		System.out.println("ColorPanel");
		
		// Set up heading and colors
		heading = new JLabel ("Choose the background color!");
		heading.setFont (new Font ("Helvetica", Font.BOLD, FONT_SIZE));
		color[0] = Color.yellow;
		color[1] = Color.cyan;
		color[2] = Color.red;
		color[3] = Color.green;
		color[4] = Color.magenta;
		
		ButtonGroup group = new ButtonGroup();
		ColorListener cListener = new ColorListener();
		
		colorButton[0] = new JRadioButton("Yellow",true);
		colorButton[1] = new JRadioButton("Cyan");
		colorButton[2] = new JRadioButton("Red");
		colorButton[3] = new JRadioButton("Green");
		colorButton[4] = new JRadioButton("Magenta");
		
		for(int i = 0; i <colorButton.length; i++)
		 {group.add(colorButton[i]);
		 colorButton[i].addActionListener(cListener);
		 colorButton[i].setBackground(Color.white);
		 colorButton[i].addActionListener(cListener);
		   add(colorButton[i]);
		 }
		
		add (heading);
		setBackground (Color.white);
		setPreferredSize (new Dimension (WIDTH, HEIGHT));
		

	}
	// ***************************************************************
	// Represents the listener for the radio buttons.
	// ***************************************************************
	private class ColorListener implements ActionListener
	{
		private static final int i = 0;

		// -------------------------------------------------------
		// Updates the background color of the panel based on
		// which radio button is selected.
		// ------------------------------------------------------
		public void actionPerformed (ActionEvent event)
		{
			for(int i = 0; i <colorButton.length; i++){
				if(colorButton[i].isSelected())
				add(colorButton[i]);}
			}
		}
	
	}

		
	
