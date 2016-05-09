package ServerListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.SynchronousQueue;

import com.google.gson.Gson;

import LoginStructs.LoginAccount;
import RegisterStructs.RegisterAccount;

public class LoginVerify extends Verified implements Runnable {

	private Thread thread;
	private Socket currentSocket;


	public LoginVerify(){
		result = LoginResults.getInstance();
		thread = new Thread(this);
	}
	
	@Override
	public void passAccount(Socket s) {
		currentSocket = s;
		System.out.println("im gever1");

		thread.start();

	}

	@Override
	public void run() {
		System.out.println("im gever2");
			DataInputStream dataInput = null;
		
		try {
			 dataInput = new DataInputStream(currentSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			String verifyString = null;
			try {
				verifyString = dataInput.readUTF();
			System.out.println(verifyString);
			Gson gson = new Gson();
			LoginAccount acc = gson.fromJson(verifyString, LoginAccount.class);
			if(checkAccount(acc)){
				result.success(currentSocket, acc);
			}else {
				result.failed(currentSocket, acc);
			}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}

	private boolean checkAccount(LoginAccount acc) {
		
		
		LoginAccount accountInDb = dataBaseLayer.getAccount(acc.getId());
		
		System.out.println(accountInDb);
		if(accountInDb == null){
			return false;
		}
		
		if(accountInDb.getName().equals(acc.getName()) && accountInDb.getPassword().equals(acc.getPassword())){
			return true;
		}
		
		return false;
	}
	

}
