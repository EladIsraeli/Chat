package ServerListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAccept implements Runnable{
	private ServerSocket acceptSocket;
	private String address;
	protected boolean isRunning;
	protected Thread thread;
	
	public ServerAccept(String address, int port) throws IOException{
		setAddress(address);
		acceptSocket = new ServerSocket(port);
		thread = new Thread(this);
		
	}

	public ServerSocket getAcceptSocket() {
		return acceptSocket;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public void StartServerToAccept(){
		setRunning(true);
		getThread().start();
	}
	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public void StopServerToAccept(){
		setRunning(false);
	}
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void setAcceptSocket(ServerSocket acceptSocket) {
		this.acceptSocket = acceptSocket;
	}

	public void run(){
		while(isRunning){
			try {
				Socket socket = getAcceptSocket().accept();
				Verification v = new Verification(socket);
				v.startVerifing();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
