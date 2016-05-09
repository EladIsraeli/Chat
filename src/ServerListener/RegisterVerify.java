package ServerListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import DataBaseLayer.DatabaseConnection;
import DataBaseLayer.IDatabase;
import LoginStructs.LoginAccount;
import RegisterStructs.RegisterAccount;

public class RegisterVerify extends Verified implements Runnable {
	
	public RegisterVerify(){
		result = RegisterResults.getInstance();
		thread = new Thread(this);
	}
	
	public void passAccount(Socket s) {
		currentSocket = s;
		
		
		thread.start();

	}
	
	private void doRegister(LoginAccount acc){
		dataBaseLayer.insertAccount(acc);
		result.success(currentSocket, acc);

	}


	@Override
	public void run() {
		DataInputStream dataInput = null;
		
		try {
			 dataInput = new DataInputStream(currentSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String verifyString = dataInput.readUTF();
			Gson gson = new Gson();
			LoginAccount acc = gson.fromJson(verifyString, LoginAccount.class);
			if(verifyExistAcc(acc)){
				doRegister(acc);
			}
			else{
				result.failed(currentSocket, acc);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private boolean verifyExistAcc(LoginAccount acc){
		if(dataBaseLayer.getAccount(acc.getId()) != null){
			return false;
		}
		
		return true;
	}

}
