package Communication;

public abstract class ComMessage {
	private String message;
	private int sender;
	private int reciever;
	
	public ComMessage(String message,int sender,int reciever){
		setReciever(reciever);
		setMessage(message);
		setSender(sender);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReciever() {
		return reciever;
	}

	public void setReciever(int reciever) {
		this.reciever = reciever;
	}
	
	
}
