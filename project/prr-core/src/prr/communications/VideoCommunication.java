package prr.communications;

import prr.client.Level;
import prr.terminals.Terminal;

public class VideoCommunication extends Communication {
	
	public final String _type = "VIDEO";
	
	public VideoCommunication(int key, Terminal sender, Terminal receiver, String message) {
		super(key, sender, receiver);
	}

	@Override
	public int definePrice(Level clientLevel) {
		return 0;
	}
	
	@Override
	public void endCommunication(int duration) {
		
	}
	
	public String getType() {
		return _type;
	}
}
