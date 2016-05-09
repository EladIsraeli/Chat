package ServerListener;

import java.net.Socket;

import DataBaseLayer.DatabaseConnection;
import DataBaseLayer.IDatabase;

public abstract class Verified implements Runnable{
	
	protected Thread thread;
	protected Socket currentSocket;
	protected IDatabase dataBaseLayer;
	protected IResult result;
	
	public Verified(){
		thread = new Thread(this);
		dataBaseLayer = new DatabaseConnection();
	}
	
	public abstract void passAccount(Socket s);
}
