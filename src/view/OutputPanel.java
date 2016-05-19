package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import controller.UIController;
import utils.Config;

import java.awt.*;

public class OutputPanel extends JPanel {

	public JTextArea[] outputs = new JTextArea[2];
	private JScrollPane[] scrollbars = new JScrollPane[2];
	
	private JTextArea keyOutput;

    public JTextArea[] getOutputs() {
		return outputs;
	}

	public void setKeyOutput(JTextArea keyOutput) {
		this.keyOutput = keyOutput;
	}

    public OutputPanel(){
    	
    	TitledBorder titled = new TitledBorder("Output");
    	this.setBorder(titled);    	

    	JPanel outHolder = new JPanel(new FlowLayout());
    	
    	for (int i=0; i<outputs.length; i++) {
    		scrollbars[i] = new JScrollPane();
    		outputs[i] = new JTextArea();

    		scrollbars[i].setBorder(BorderFactory.createEmptyBorder());
    		scrollbars[i].getViewport().setBackground(Color.WHITE);
    		scrollbars[i].setViewportView(outputs[i]);
    		scrollbars[i].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    		scrollbars[i].getVerticalScrollBar().setUnitIncrement(50);
    		scrollbars[i].setPreferredSize(new Dimension((Config.getWidth()-30)/outputs.length, 220));
    		
    		outputs[i].setEditable(false);
    		outputs[i].setForeground(Color.BLACK);
    		outputs[i].setBackground(Color.WHITE);
    		outputs[i].setFont(new Font("Sans-serif", Font.BOLD, 10));		
    		outputs[i].setLineWrap(true);
    		outputs[i].setWrapStyleWord(true);

    		DefaultCaret caret2 = (DefaultCaret)outputs[i].getCaret();
    		caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    		
    		outHolder.add(scrollbars[i]);
    	}
    	
    	outputs[0].setBackground(new Color(239, 239, 239));
    	
    	//keyOutput = outputs[0];
		this.add(outHolder);
		
    }
    
}
