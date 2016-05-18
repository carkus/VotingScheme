package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBarUI extends JMenuBar {
	
	public MenuBarUI () {
		buildMenuBar();
	}
	
	private void buildMenuBar() {
		
    	System.out.println("menuBar");
		
        //JMenuBar menuBar = new JMenuBar();
        
        // Add a function menu
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        this.add(menu);
        
        // Allow user to reset the maximum value for all algorithms
        JMenuItem maxValueMenuItem = new JMenuItem("Change Max Value");
        maxValueMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //displayMaxValueDialog();
            }
        });
        menu.add(maxValueMenuItem);
        
        // Allow user to generate a random prime number within given range
        JMenuItem primeMenuItem = new JMenuItem("Prime Generator");
        primeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //displayPrimeDialog();
            }
        });
        menu.add(primeMenuItem);
        
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
        
        
        // Add space between two menu items
        /*JMenu empty = new JMenu(" ");
        empty.setEnabled(false);
        
        menuBar.add(empty);*/
        
    }

}
