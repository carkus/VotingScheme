package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.UIController;

public class MenuBarUI extends JMenuBar {
	
	public MenuBarUI () {
		buildMenuBar();
	}
	
	private void buildMenuBar() {
		
    	System.out.println("menuBar");

        // Add a function menu
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        this.add(menu);
        
        // Allow user to reset the maximum value for all algorithms
        JMenuItem maxValueMenuItem = new JMenuItem("Options");
        maxValueMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //MediatorUI.getInstance().showOptionsPane();
            	UIController.getInstance().getOptionPanel().setVisible(true);
            }
        });
        menu.add(maxValueMenuItem);
        
        // Allow user to generate a random prime number within given range
        /*JMenuItem primeMenuItem = new JMenuItem("Prime Generator");
        primeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //displayPrimeDialog();
            }
        });
        menu.add(primeMenuItem);*/
        
        // Exit button
        menu.addSeparator();
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitMenuItem);

    }


}
