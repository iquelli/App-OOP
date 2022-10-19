package prr.communications;

public class VoiceCommunication extends Communication {
	private final String _type = "VOICE";

	public VoiceCommunication(String key, String senderId, String receiverId, int units, int price, boolean status) {
		super(key, senderId, receiverId, units, price, status);
	}

	public String getType() {
		return _type;
	}
}