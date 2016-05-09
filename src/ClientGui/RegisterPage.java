package ClientGui;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

import ClientGui.LoginPage.buttonAction;
import ClientGui.LoginPage.enterServerButtonListener;
import ClientManagement.Client;
import ClientManagement.ClientEntity;
import ClientManagement.IManager;
import Communication.ComMessage;
import LoginStructs.LoginAccount;


public class RegisterPage implements IManager {   

		JFrame preFrame;
		JTextField usernameChooser;
		JTextField passwordChooser;
		JTextField idChooser;
		Client client;
		String appName = "Elad Israeli Chat v0.1";
		
		MainPage mainPage;
		
		
		public static int port = 33333;

      JScrollPane scrollpane;
      public RegisterPage(MainPage mainPage) {
         this.mainPage = mainPage;
    	  
    	  	preFrame = new JFrame(appName);
	        usernameChooser = new JTextField(15);
	        passwordChooser = new JTextField(15);
	        idChooser = new JTextField(15);
	        JLabel chooseUsernameLabel = new JLabel("UserName");
	        JLabel choosePasswordLabel = new JLabel("Password");
	        JLabel chooseIdLabel = new JLabel("Id");


	        JButton enterServer = new JButton("Register");
	        enterServer.addActionListener(new enterServerButtonListener());
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

	        prePanel.add(chooseUsernameLabel, preLeft);
	        prePanel.add(usernameChooser, preRight);
	        
	        prePanel.add(choosePasswordLabel, preLeft);
	        prePanel.add(passwordChooser, preRight);
	        
	        prePanel.add(chooseIdLabel, preLeft);
	        prePanel.add(idChooser, preRight);
	        
	        
	        JLabel LoginLabel = new JLabel();
	        LoginLabel.setText("Registeration Page");
	        
	        preFrame.add(BorderLayout.NORTH, LoginLabel);
	        
	        preFrame.add(BorderLayout.CENTER, prePanel);
	        preFrame.add(BorderLayout.SOUTH, enterServer);
	        preFrame.setSize(300, 300);
	        preFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        preFrame.setLocation(dim.width/2-preFrame.getSize().width/2, dim.height/2-preFrame.getSize().height/2);
	        
      }

      class enterServerButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				   String username = usernameChooser.getText();
				   String password = passwordChooser.getText();
		    	if(idChooser.getText().length() < 1 || username.length() < 1 && password.length() < 1 || idChooser.getText().length() < 1){
	        		typeNothing();
	        		System.out.println("NO!!!");
	        		return;
	        		
	        	}
	        	
				

	           int id = Integer.parseInt(idChooser.getText());
	           
	            if (username.length() < 1 && password.length() < 1 && idChooser.getText().length() < 1) {
	                System.out.println("No!");
	                
	                
	               
	            } else {
	            	
	                register(username, password, id);
	              
	         
	            }
	        }


      }
      void typeNothing(){
	    	
	    	preFrame = new JFrame(appName);
	        JLabel failed = new JLabel();
	        failed.setText("Error you have typed nothing!");

	      	     
	        preFrame.add(BorderLayout.CENTER, failed);
	        preFrame.setSize(175, 175);
	        preFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        preFrame.setLocation(dim.width/2-preFrame.getSize().width/2, dim.height/2-preFrame.getSize().height/2);
	    }
	    
      
      public void registerCompleted(){
    	  
    	  
  	  		preFrame = new JFrame(appName);
	        JLabel succeed = new JLabel();
	        succeed.setText("Register Succeeded!!");

	        JButton buttonToFirstPage = new JButton();
	 	   buttonToFirstPage.setText("Back To First Page");     
	        
	 	        buttonToFirstPage.addActionListener(new ActionListener(){

	 				@Override
	 				public void actionPerformed(ActionEvent arg0) {
	 					
	 					preFrame.setVisible(false);
	 					
	 					mainPage.preFrame.setVisible(true);
	 				}
	 	        	
	 	        	
	 	        	
	 	        });
	 	     
	 	    preFrame.add(BorderLayout.SOUTH, buttonToFirstPage);
	        preFrame.add(BorderLayout.CENTER, succeed);
	        preFrame.setSize(300, 300);
	        preFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        preFrame.setLocation(dim.width/2-preFrame.getSize().width/2, dim.height/2-preFrame.getSize().height/2);
    	  
      }
      
      public void registerFailed(){
    	  
    	  
	  		preFrame = new JFrame(appName);
	        JLabel failed = new JLabel();
	        failed.setText("Register Failed!!");

       JButton buttonToFirstPage = new JButton();
	   buttonToFirstPage.setText("Back To First Page");     
       
	        buttonToFirstPage.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					preFrame.setVisible(false);
					
					mainPage.preFrame.setVisible(true);
				}
	        	
	        	
	        	
	        });
	     
	        preFrame.add(BorderLayout.SOUTH, buttonToFirstPage);
	        preFrame.add(BorderLayout.CENTER, failed);
	        preFrame.setSize(300, 300);
	        preFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        preFrame.setLocation(dim.width/2-preFrame.getSize().width/2, dim.height/2-preFrame.getSize().height/2);
  	  
    }
	    
	    public void createClient(String username, String password, int id){
	    	Socket socket = null;
			try {
				socket = new Socket("127.0.0.1", port);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	client = new ClientEntity(username, password, id, socket, this);
	    	client.getReaderThread().startThread();
	    	
	    	System.out.println(client.getId());
	    }
		
		public void register(String username, String password, int id) {
			createClient(username, password, id);
			client.getWriterThread().writeToClient("2");
	    	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	LoginAccount acc = new LoginAccount(username, id, password);
	    	Gson gson = new Gson();
	    	
	    	String toSend = gson.toJson(acc);
			client.getWriterThread().writeToClient(toSend);

		}
	    
      
//      public static void main(String [] args){
//    	  SwingUtilities.invokeLater(new Runnable() {
//	            @Override
//	            public void run() {
//	                try {
//	                    UIManager.setLookAndFeel(UIManager
//	                            .getSystemLookAndFeelClassName());
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
//	                RegisterPage mainGUI = new RegisterPage();
//	            }
//	            
//    	  });
//      }
      
      
  	@Override
	public void incommingMessage(String s) {
		System.out.println("succeed " + s);
		if(s.equals("11")){
			 preFrame.setVisible(false);
			 registerCompleted();
              return;
		}else if(s.equals("12")){
			preFrame.setVisible(false);
			registerFailed();
			return;
		}
		
	
		
	}
      
      
}



    	  
