package ClientGui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ClientGui.LoginPage.enterServerButtonListener;

public class MainPage {

	JFrame preFrame;
	JButton Login, Register;
	String appName = "Elad Israeli Chat v0.1";
	
	
	public static MainPage mainPage;

	public static void main(String [] args){
		
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager
	                            .getSystemLookAndFeelClassName());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                MainPage mainGUI = new MainPage();
	                mainPage = mainGUI;
	                mainGUI.display();
	            }
	        });
     }

	
	
	void display(){
		
		   preFrame = new JFrame(appName);
		   Login = new JButton();
		   Register = new JButton();
		   
		   Login.setText("Login");
		   Register.setText("Register");

		   Login.addActionListener(new enterRegister());
		   Register.addActionListener(new enterLogin());

	        JPanel prePanel = new JPanel(new GridBagLayout());

	        GridBagConstraints preRight = new GridBagConstraints();
	        preRight.insets = new Insets(0, 0, 0, 10);
	        preRight.anchor = GridBagConstraints.EAST;
	        GridBagConstraints preLeft = new GridBagConstraints();
	        preLeft.anchor = GridBagConstraints.WEST;
	        preLeft.insets = new Insets(0, 10, 0, 10);
	        // preRight.weightx = 2.0;
	        preRight.fill = GridBagConstraints.HORIZONTAL;
	        preRight.gridwidth = GridBagConstraints.REMAINDER;

	        prePanel.add(Login, preLeft);
	        prePanel.add(Register, preRight);
	        

	        
	     
	        
	        preFrame.add(BorderLayout.CENTER, prePanel);
	        preFrame.setSize(300, 300);
	        preFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        preFrame.setLocation(dim.width/2-preFrame.getSize().width/2, dim.height/2-preFrame.getSize().height/2);
	}
	
	   class enterRegister implements ActionListener {
       	
	        public void actionPerformed(ActionEvent event) {
	        	
	        	preFrame.setVisible(false);
	        	
	            SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                try {
		                    UIManager.setLookAndFeel(UIManager
		                            .getSystemLookAndFeelClassName());
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		                LoginPage mainGUI = new LoginPage(mainPage);
		                mainGUI.preDisplay();
		            }
		        });
	        }

	    }
       
       class enterLogin implements ActionListener {
       	
	        public void actionPerformed(ActionEvent event) {
	        	preFrame.setVisible(false);

	        	
	        	SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                try {
		                    UIManager.setLookAndFeel(UIManager
		                            .getSystemLookAndFeelClassName());
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		                RegisterPage mainGUI = new RegisterPage(mainPage);
		            }
		            
	    	  });
	        }

	    }
}
