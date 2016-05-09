package ServerListener;

import java.net.Socket;

import LoginStructs.LoginAccount;

public interface IResult {
	
	void success(Socket s, LoginAccount acc);
	void failed(Socket s, LoginAccount acc);

}
