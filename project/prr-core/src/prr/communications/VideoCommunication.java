package prr.communications;

public class VideoCommunication extends Communication {
	public final String _type = "VIDEO";
	
	public VideoCommunication(String key, String senderId, String receiverId, int units, int price, boolean status) {
		super(key, senderId, receiverId, units, price, status);
	}
	
	public String getType() {
		return _type;
	}
}
