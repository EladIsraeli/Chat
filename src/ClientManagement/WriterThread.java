package ClientManagement;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriterThread  {
	private DataOutputStream output;

	public DataOutputStream getOutput() {
		return output;
	}

	public void setOutput(DataOutputStream output) {
		this.output = output;
	}



	public WriterThread(OutputStream output){
		this.output = new DataOutputStream(output);
		

	}
	public void writeToClient(String s){
		try {
			output.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
