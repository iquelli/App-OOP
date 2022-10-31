package prr.communications;

import prr.client.Level;
import prr.terminals.Terminal;

public class TextCommunication extends Communication {
	
	private static final String _type = "TEXT";
	
	private String _message;
	
	public TextCommunication(int key, Terminal sender, Terminal receiver, String message) {
		super(key, sender, receiver);
		_message = message;
		super.endCommunication(0);
	}
	
	@Override
	public int definePrice(Level clientLevel) {
		return 0;
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

