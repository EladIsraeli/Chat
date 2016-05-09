package Communication;

public class ComMessage {
	private String message;
	


	private int sender;
	private String nameSender;
	
	private int reciever;
	private String nameReciever;
	
	public ComMessage(String message,int sender,int reciever, String nameSender){
		setReciever(reciever);
		setMessage(message);
		setSender(sender);
		setNameSender(nameSender);
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
	
	public String getNameSender() {
		return nameSender;
	}

	public void setNameSender(String nameSender) {
		this.nameSender = nameSender;
	}

	public String getNameReciever() {
		return nameReciever;
	}

	public void setNameReciever(String nameReciever) {
		this.nameReciever = nameReciever;
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
