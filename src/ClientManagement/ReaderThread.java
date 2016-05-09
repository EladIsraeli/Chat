package ClientManagement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReaderThread extends AbstractReaderThread {
	

	public ReaderThread(InputStream inputStream, IManager manager){
		super(inputStream, manager);
		
	}
	
	@Override
	public void run() {
		while(isRunning()){
			try {
				String s = input.readUTF();
				System.out.println(s);
				clientManager.incommingMessage(s);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
