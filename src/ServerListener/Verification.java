package ServerListener;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Verification implements Runnable{
	private Socket socket;
	private Thread thread;
	private boolean Verified;
	private Verified afterVerified;
	
	public boolean isVerified() {
		return Verified;
	}


	public void setVerified(boolean verified) {
		Verified = verified;
	}


	public Verification(Socket socket) 
	{
		 setSocket(socket);
		 thread = new Thread(this);
	}

	    
	public Socket getSocket() {
		return socket;
	}


	public void setSocket(Socket socket) {
		this.socket = socket;
	}


	public void startVerifing(){
		 setVerified(false);
		thread.start();
	}


	@Override
	public void run() {
		System.out.println("bla");

		while(!isVerified()){
			DataInputStream dataInput = null;
			
			try {
				 dataInput = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				String verifyString = dataInput.readUTF();
				if(verifyString.equals("1")){
					afterVerified = new LoginVerify();
				}
				else if(verifyString.equals("2")){
					afterVerified = new RegisterVerify();
					
				}
				
				afterVerified.passAccount(socket);
				setVerified(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
}
