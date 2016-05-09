package ServerListener;

import java.io.IOException;

public class RunServer {

	public static void main(String[] args) {
		try {
			ServerAccept server = new ServerAccept("bla", 33333);
			server.StartServerToAccept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
