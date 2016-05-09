package ClientManagement;

import java.io.DataInputStream;
import java.io.InputStream;

public abstract class AbstractReaderThread implements Runnable {
	protected DataInputStream input;
	protected Thread thread;
	protected boolean running;
	
	protected IManager clientManager;


	
	public AbstractReaderThread(InputStream inputStream, IManager manager){
		input = new DataInputStream(inputStream);
		thread = new Thread(this);
		
		clientManager = manager;

	}
	
	public DataInputStream getInput() {
		return input;
	}


	public void setInput(DataInputStream input) {
		this.input = input;
	}

	public Thread getThread() {
		return thread;
	}


	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void startThread(){
		setRunning(true);
		thread.start();
		
	}



}
