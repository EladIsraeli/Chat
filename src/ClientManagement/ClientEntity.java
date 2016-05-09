package ClientManagement;

import java.io.IOException;
import java.net.Socket;

public class ClientEntity extends Client{

	public ClientEntity(String name, String password, int id, Socket connectionSocket, IManager manager) {
		super(name, password, id, connectionSocket);
		
		try {
			readerThread = new ClientReaderThread(connectionSocket.getInputStream(), manager);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
