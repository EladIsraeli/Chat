package ClientManagement;

import java.io.IOException;
import java.io.InputStream;

public class ClientReaderThread extends AbstractReaderThread{

	public ClientReaderThread(InputStream inputStream, IManager manager) {
		super(inputStream, manager);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void run() {
		
		boolean flag = false;
		
		String inputString = null;
		
		try {
			 inputString = input.readUTF();
			 System.out.println("bla");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		clientManager.incommingMessage(inputString);
		

			if(inputString.equals("10")){
				setRunning(false);
			}
			else if(inputString.equals("9")){
				System.out.println("there");
				setRunning(true);
			}
		
		while(isRunning()){
			
			try {
				inputString = input.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				clientManager.incommingMessage(inputString);
			
			
		
			
		}
		
	}

}
