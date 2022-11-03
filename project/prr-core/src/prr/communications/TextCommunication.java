package prr.communications;

import prr.client.Client.Level;
import prr.terminals.Terminal;

public class TextCommunication extends Communication {
	
	private static final String _type = "TEXT";
	
	private String _message;
	
	public TextCommunication(int key, Terminal sender, Terminal receiver, String message) {
		super(key, sender, receiver);
		_message = message;
		super.endCommunication(0);
		super.getSender().getClient().madeTextCommunication();
	}
	
	@Override
	public int definePrice(Level clientLevel) {
		int length = _message.length();

		int normalPrice = clientLevel.getTariff().getMessagePrice(length);
		return getSender().isFriendWith(getReceiver()) ? normalPrice / 2 : normalPrice;
	}
	
	@Override
	public int getUnits() {
		return _message.length();
	}
	
	@Override
	public String getType() {
		return _type;
	}
}

