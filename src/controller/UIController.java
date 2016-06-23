package controller;

import javax.swing.*;

import view.RegistrationPanel;
import view.ReviewPanel;
import view.BallotPanel;
import view.InstructionPanel;
import view.MainPanel;
import view.OptionFrame;
import view.TallyPanel;
import view.VotingFrame;
import view.BallotPanel.VotingListener;

public class UIController {

	private static UIController instance;

	private VotingFrame votingFrame;
	private JTextArea[] outputs = new JTextArea[2];
	
	private InstructionPanel instructionPanel;
	private RegistrationPanel registrationPanel;
	private BallotPanel ballotPanel;
	private ReviewPanel reviewPanel;
	private MainPanel mainPanel;

	private TallyPanel tallyPanel;
	private OptionFrame optionFrame;

	private UIController(){ }
	
	public static synchronized UIController getInstance() {
		if (instance == null) {
			instance = new UIController();
		}
		return instance;
	}
    
    public void setupUI() {    	
    	optionFrame = new OptionFrame();
    	optionFrame.setVisible(false);
    	votingFrame = new VotingFrame();
    	outputs = votingFrame.getVotingPanel().getOutputPanel().getOutputs();
    }
    
	public void disableVoting() {
		for(int i = 0; i <ballotPanel.votingButton.length; i++) {
			if (!ballotPanel.votingButton[i].isSelected()) {
				ballotPanel.votingButton[i].setEnabled(false);
			}
		}
	}
	public void enableVoting() {
		for(int i = 0; i <ballotPanel.votingButton.length; i++) {
			ballotPanel.votingButton[i].setEnabled(true);
		}
	}
    
    public void out(String msg, int i) {
    	outputs[i].append(msg + "\n");
    }
    public void clearOutputs() {
    	for (int i=0; i<outputs.length; i++) {
    		outputs[i].setText("");    		
    	}
    }
 
	public RegistrationPanel getRegistrationPanel() {
		return registrationPanel;
	}

	public void setRegistrationPanel(RegistrationPanel registrationPanel) {
		this.registrationPanel = registrationPanel;
	}

	public BallotPanel getBallotPanel() {
		return ballotPanel;
	}

	public void setBallotPanel(BallotPanel ballotPanel) {
		this.ballotPanel = ballotPanel;
	}

	public InstructionPanel getInstructionPanel() {
		return instructionPanel;
	}

	public void setInstructionPanel(InstructionPanel instructionPanel) {
		this.instructionPanel = instructionPanel;
	}

	public TallyPanel getTallyPanel() {
		return tallyPanel;
	}

	public void setTallyPanel(TallyPanel tallyPanel) {
		this.tallyPanel = tallyPanel;
	}

	public OptionFrame getOptionPanel() {
		return optionFrame;
	}

	public ReviewPanel getReviewPanel() {
		return reviewPanel;
	}

	public void setReviewPanel(ReviewPanel reviewPanel) {
		this.reviewPanel = reviewPanel;
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}
	
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
    /**
     * A helper method that help to layout the given child component on the
     * parent component with the GridBagConstraints values.
     * @param parent the parent JComponent
     * @param children the child JComponent
     * @param gridx specifies the cell containing the leading edge of the 
     *        component's display area, where the first cell in a row has gridx=0
     * @param gridy specifies the cell at the top of the component's display 
     *        area, where the topmost cell has gridy=0
     * @param gridwidth specifies the number of cells in a row for the 
     *        component's display area
     * @param gridheight specifies the number of cells in a column for the 
     *        component's display area
     * @param weightx specifies how to distribute extra horizontal space
     * @param weighty Specifies how to distribute extra vertical space
     * @param fill this field is used when the component's display area is 
     *        larger than the component's requested size. It determines whether to resize the component, and if so, how
     * @param insets this field specifies the external padding of the component, 
     *        the minimum amount of space between the component and the edges of its display area
     */
    /*public void setComponent(JComponent parent, JComponent child,
            int gridx, int gridy, int gridwidth, int gridheight, 
            int weightx, int weighty, int fill, Insets insets) {        
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.weightx = weightx;
        c.weighty = weighty;
        c.fill = fill;
        c.insets = insets == null ? new Insets(0,0,0,0) : insets;
        parent.add(child, c);  
    }

    private static final int MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();*/


}
