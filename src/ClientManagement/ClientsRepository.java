package ClientManagement;


import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import Communication.ComMessage;
import SharedPackage.IAccepts;

public class ClientsRepository implements IAccepts, IManager{
	private HashMap<Integer, Client> clients;
	
private static ClientsRepository firstInstance = null;
	
	private ClientsRepository(){
		setClients(new HashMap<Integer, Client>());

	}
	
	public static ClientsRepository getInstance(){
		if(firstInstance == null){
			firstInstance = new ClientsRepository();
			
		}
		
		return firstInstance;
	}
	
	


	public HashMap<Integer, Client> getClients() {
		return clients;
	}

	public void setClients(HashMap<Integer, Client> clients) {
		this.clients = clients;
	}
	
	public void setClient(Client client){
		clients.put(client.getId(), client);
	}

	@Override
	public void incommingMessage(String s) {
		System.out.println("stat");
		Gson gson = new Gson();
		
		ComMessage msg = gson.fromJson(s, ComMessage.class);
		Client c = clients.get(msg.getReciever());
		c.getWriterThread().writeToClient(s);
		
	}

	@Override
	public void setClient(Socket socket) {
		// TODO Auto-generated method stub
		
	}
}
