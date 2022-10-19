package prr.communications;

public class TextCommunication extends Communication {
	public final String _type = "TEXT";
	
	public TextCommunication(String key, String senderId, String receiverId, int units, int price, boolean status) {
		super(key, senderId, receiverId, units, price, status);
	}
	
	public String getType() {
		return _type;
	}
}

