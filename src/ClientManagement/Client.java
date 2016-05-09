package ClientManagement;

import java.io.IOException;
import java.net.Socket;


public abstract class Client {
	protected String name;
	protected int id;
	protected String password;
	protected Socket connectionSocket;
	
	static Integer nextId = null;
	
	protected AbstractReaderThread readerThread;
	protected WriterThread writerThread;
	
	

	public AbstractReaderThread getReaderThread() {
		return readerThread;
	}

	public void setReaderThread(ReaderThread readerThread) {
		this.readerThread = readerThread;
	}


	public WriterThread getWriterThread() {
		return writerThread;
	}


	public void setWriterThread(WriterThread writerThread) {
		this.writerThread = writerThread;
	}


	public Socket getConnectionSocket() {
		return connectionSocket;
	}


	public void setConnectionSocket(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
	}


	public Client(String name, String password, int id, Socket connectionSocket){

		setName(name);
		setPassword(password);
		setId(id);
		setConnectionSocket(connectionSocket);
		try {
			writerThread = new WriterThread(connectionSocket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(writerThread);
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
