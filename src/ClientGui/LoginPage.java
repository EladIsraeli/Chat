package ClientGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.google.gson.Gson;

import ClientManagement.Client;
import ClientManagement.ClientEntity;
import ClientManagement.IManager;
import Communication.ComMessage;
import LoginStructs.LoginAccount;

	public class LoginPage implements IManager{

		
		ArrayList<Integer> accounts;
		
	    String appName = "Elad Israeli Chat v0.1";
	    LoginPage  mainGUI;
	    JFrame newFrame    = new JFrame(appName);
	    
	    JFrame userListFrame;
	    JButton sendMessage;
	    JTextField messageBox;
	    JTextArea  chatBox;
	    JTextField usernameChooser;
	    JTextField passwordChooser;
	    JTextField idChooser;
	    JTextField userByIdField;
	    JFrame preFrame;
	    
	    
	    
	    
	    static int port = 33333;
	    
	    
	    Client client;

	    
	    public LoginPage(){
	    	accounts = new ArrayList<Integer>();
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
	    
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager
	                            .getSystemLookAndFeelClassName());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                LoginPage mainGUI = new LoginPage();
	                mainGUI.preDisplay();
	            }
	        });
	    }
	    
	    public void preDisplay() {
	        newFrame.setVisible(false);
	        

	        preFrame = new JFrame(appName);
	        usernameChooser = new JTextField(15);
	        passwordChooser = new JTextField(15);
	        idChooser = new JTextField(15);
	        JLabel chooseUsernameLabel = new JLabel("UserName");
	        JLabel choosePasswordLabel = new JLabel("Password");
	        JLabel chooseIdLabel = new JLabel("Id");


	        JButton enterServer = new JButton("Login");
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
	        
	        preFrame.add(BorderLayout.CENTER, prePanel);
	        preFrame.add(BorderLayout.SOUTH, enterServer);
	        preFrame.setSize(300, 300);
	        preFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        preFrame.setLocation(dim.width/2-preFrame.getSize().width/2, dim.height/2-preFrame.getSize().height/2);
	        
	        

	    }
	    public void chatList(){
	    	userListFrame = new JFrame(appName);
	    	preFrame.setVisible(false);
	        
	        
	        userByIdField = new JTextField(15);
	        JLabel userById = new JLabel("Add User By Id");

	        JButton enterServer = new JButton("Add");
	        JPanel prePanel = new JPanel(new GridBagLayout());

	        
	        enterServer.addActionListener(new enterUserButtonListener(prePanel));
	        
	        

	        GridBagConstraints preRight = new GridBagConstraints();
	        preRight.insets = new Insets(0, 0, 0, 10);
	        preRight.anchor = GridBagConstraints.EAST;
	        GridBagConstraints preLeft = new GridBagConstraints();
	        preLeft.anchor = GridBagConstraints.WEST;
	        preLeft.insets = new Insets(0, 10, 0, 10);
	        // preRight.weightx = 2.0;
	        preRight.fill = GridBagConstraints.HORIZONTAL;
	        preRight.gridwidth = GridBagConstraints.REMAINDER;

	        prePanel.add(userById, preLeft);
	        prePanel.add(userByIdField, preRight);
	        
	        JLabel infoLabel = new JLabel();
	        infoLabel.setText("Name: " + client.getName() + " Id: " + client.getId());
	        
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        userListFrame.setLocation(dim.width/2-userListFrame.getSize().width/2, dim.height/2-userListFrame.getSize().height/2);
	        
	        userListFrame.add(BorderLayout.NORTH, infoLabel);
	        userListFrame.add(BorderLayout.CENTER, prePanel);
	        userListFrame.add(BorderLayout.SOUTH, enterServer);
	        userListFrame.setSize(300, 300);
	        userListFrame.setVisible(true);
	    	
	    }
	    
	    class enterUserButtonListener implements ActionListener {
	    	
	    	private JPanel prePanel;
	    	
	        public enterUserButtonListener(JPanel prePanel) {
	        	this.prePanel = prePanel;
			}

			public void actionPerformed(ActionEvent event) {
	        	String userToAdd = userByIdField.getText();
	        	accounts.add(Integer.parseInt(userToAdd));
	        	addAccount(Integer.parseInt(userToAdd));
	        }
			
			public void addAccount(int id){
				
				JButton button = new JButton(id+"");
				button.addActionListener(new buttonAction(button));
				
		        GridBagConstraints preRight = new GridBagConstraints();
		        preRight.insets = new Insets(0, 0, 0, 10);
		        preRight.anchor = GridBagConstraints.EAST;
		        GridBagConstraints preLeft = new GridBagConstraints();
		        preLeft.anchor = GridBagConstraints.WEST;
		        preLeft.insets = new Insets(0, 10, 0, 10);
		        // preRight.weightx = 2.0;
		        preRight.fill = GridBagConstraints.HORIZONTAL;
		        preRight.gridwidth = GridBagConstraints.REMAINDER;

		        JLabel userName = new JLabel();
		        userName.setText("User: ");
		        
		        prePanel.add(userName, preLeft);
		        prePanel.add(button, preRight);
		        
		        prePanel.revalidate();
		        prePanel.repaint();
		        
		        
		        
			}

	    }
	    
	    
	    class buttonAction implements ActionListener {
	    	
	    	private JButton button;
	    	
	        public buttonAction(JButton button) {
	        	this.button = button;
	        }

			public void actionPerformed(ActionEvent event) {
	        	int id = Integer.parseInt(button.getText());
				display(id);

	        	
	        	
	        }
	          

	    }

	    public void display(int id) {
	    	userListFrame.setVisible(false);
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BorderLayout());

	        JPanel southPanel = new JPanel();
	        southPanel.setBackground(Color.BLUE);
	        southPanel.setLayout(new GridBagLayout());

	        messageBox = new JTextField(30);
	        messageBox.requestFocusInWindow();

	        sendMessage = new JButton("Send Message");
	        sendMessage.addActionListener(new sendMessageButtonListener(id));

	        chatBox = new JTextArea();
	        chatBox.setEditable(false);
	        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
	        chatBox.setLineWrap(true);

	        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

	        GridBagConstraints left = new GridBagConstraints();
	        left.anchor = GridBagConstraints.LINE_START;
	        left.fill = GridBagConstraints.HORIZONTAL;
	        left.weightx = 512.0D;
	        left.weighty = 1.0D;

	        GridBagConstraints right = new GridBagConstraints();
	        right.insets = new Insets(0, 10, 0, 0);
	        right.anchor = GridBagConstraints.LINE_END;
	        right.fill = GridBagConstraints.NONE;
	        right.weightx = 1.0D;
	        right.weighty = 1.0D;
	        
	        newFrame.getRootPane().setDefaultButton(sendMessage);

	        southPanel.add(messageBox, left);
	        southPanel.add(sendMessage, right);

	        mainPanel.add(BorderLayout.SOUTH, southPanel);

	        JLabel infoLabel = new JLabel();
	        infoLabel.setText("Id Number(Me): " + client.getId() + " Talks With " + id);
	        
	        mainPanel.add(BorderLayout.NORTH, infoLabel);
	        
	        JButton backButton = new JButton();
	        backButton.setText("Back");
	        
	        backButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					userListFrame.setVisible(true);
					newFrame.dispose();
					newFrame = new JFrame(appName);	
				}
	        	
	        	
	        });
	        
	        mainPanel.add(BorderLayout.WEST, backButton);

	        
	        newFrame.add(mainPanel);
	        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        newFrame.setSize(470, 300);
	        newFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        newFrame.setLocation(dim.width/2-newFrame.getSize().width/2, dim.height/2-newFrame.getSize().height/2);
	        
	        
	        
	        
	    }

	    class sendMessageButtonListener implements ActionListener {
	    	
	    	private int id;
	        public sendMessageButtonListener(int id) {

	        	this.id = id;
	        }

			public void actionPerformed(ActionEvent event) {
	        	ComMessage msg = new ComMessage(messageBox.getText(), id, client.getId(), client.getName());
	        	Gson gson = new Gson();
	        	String toSend = gson.toJson(msg);
	        	client.getWriterThread().writeToClient(toSend);
				
	            if (messageBox.getText().length() < 1) {
	                // do nothing
	            } else if (messageBox.getText().equals(".clear")) {
	                chatBox.setText("Cleared all messages\n");
	                messageBox.setText("");
	            } else {
	                chatBox.append("<" + id + ">:  " + messageBox.getText()
	                        + "\n");
	                messageBox.setText("");
	            }
	            messageBox.requestFocusInWindow();
	            
	

	        }
	    }

	    String  username;
	    int id;
	    class enterServerButtonListener implements ActionListener {
	
	        public void actionPerformed(ActionEvent event) {
	            username = usernameChooser.getText();
	            id = Integer.parseInt(idChooser.getText());
	            String password = passwordChooser.getText();
	            if (username.length() < 1 && password.length() < 1) {
	                System.out.println("No!");
	   
	                
	               
	            } else {
	            	
	                auth(username, password, id);
	              
	         
	            }
	        }

	    }
	    
	    public void auth(String username, String password, int id){
	    	createClient(username, password, id);
	    	
	    	client.getWriterThread().writeToClient("1");
	    	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	
	    	//check if ok then:
	    	
	    	
	    	Gson gson = new Gson();
	    	LoginAccount acc = new LoginAccount(client.getName(), client.getId(), client.getPassword());
	    	String toSend = gson.toJson(acc);
	    	client.getWriterThread().writeToClient(toSend);
	    	
	    }
	    
	    public void badLogin(){
	    	
	  		preFrame = new JFrame(appName);
	        JLabel failed = new JLabel();
	        failed.setText("Login Failed!!");

	     
	        preFrame.add(BorderLayout.CENTER, failed);
	        preFrame.setSize(300, 300);
	        preFrame.setVisible(true);
	        
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        preFrame.setLocation(dim.width/2-preFrame.getSize().width/2, dim.height/2-preFrame.getSize().height/2);
	    }

		@Override
		public void incommingMessage(String s) {
			System.out.println("Icoming " + s);
			if(s.equals("9")){
				 preFrame.setVisible(false);
	             chatList();
	              return;
			}else if(s.equals("10")){
				preFrame.setVisible(false);
				badLogin();
				return;
			}
			
			Gson gson = new Gson();
			ComMessage msg = gson.fromJson(s, ComMessage.class);
			
			chatBox.append("<" + msg.getSender() + ">:  " + msg.getMessage()
            + "\n");
			
		}
	}


