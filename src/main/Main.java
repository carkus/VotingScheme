package main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.*;

public class Main {
	
	static UIController uiController;	
	static VotingController systemController;	

	public static void main(String[] args) {

		//INSTANTIATE ALL CONTROLLERS 
		systemController = VotingController.getInstance();
		systemController.populateCandidateString();
		
		uiController = UIController.getInstance();
		uiController.setupUI();
				
		System.out.println("All Controllers constructed");
		
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // not doing anything
            }
        }
	
				
	}
	
}
