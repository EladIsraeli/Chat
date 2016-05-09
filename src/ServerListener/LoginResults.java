package ServerListener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ClientManagement.ClientsRepository;
import ClientManagement.ServerClient;
import LoginStructs.LoginAccount;

public class LoginResults implements IResult{
	private static LoginResults firstInstance = null;
	
	private LoginResults(){}
	
	public static LoginResults getInstance(){
		if(firstInstance == null){
			firstInstance = new LoginResults();
			
		}
		
		return firstInstance;
	}
	
	
	@Override
	public void success(Socket s, LoginAccount acc) {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		DataOutputStream output = null;
		try {
			output = new DataOutputStream(s.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			System.out.println("here");
			output.writeUTF("9");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ServerClient serverClient = new ServerClient(acc.getName(), acc.getPassword(), acc.getId(), s, ClientsRepository.getInstance());
		ClientsRepository.getInstance().setClient(serverClient);
		serverClient.getReaderThread().startThread();
		
	}

	@Override
	public void failed(Socket s, LoginAccount acc) {
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		DataOutputStream output = null;
		try {
			output = new DataOutputStream(s.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			System.out.println("here");
			output.writeUTF("10");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

