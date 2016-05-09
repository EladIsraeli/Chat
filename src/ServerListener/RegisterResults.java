package ServerListener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import LoginStructs.LoginAccount;

public class RegisterResults implements IResult{
	private static RegisterResults firstInstance = null;
	
	private RegisterResults(){}
	
	public static RegisterResults getInstance(){
		if(firstInstance == null){
			firstInstance = new RegisterResults();
			
		}
		
		return firstInstance;
	}
	
	
	@Override
	public void success(Socket s, LoginAccount acc) {
		
		DataOutputStream output = null;
		try {
			output = new DataOutputStream(s.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			output.writeUTF("11");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void failed(Socket s, LoginAccount acc) {
		
		DataOutputStream output = null;
		try {
			output = new DataOutputStream(s.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			output.writeUTF("12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	


}
