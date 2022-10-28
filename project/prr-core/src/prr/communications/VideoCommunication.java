package prr.communications;

import prr.client.Level;
import prr.terminals.Terminal;

public class VideoCommunication extends Communication {
	
	public final String _type = "VIDEO";
	private int _duration;
	
	public VideoCommunication(int key, Terminal sender, Terminal receiver) {
		super(key, sender, receiver);
		_duration = 0;
	}

	@Override
	public int definePrice(Level clientLevel) {
		return 0;
	}
	
	@Override
	public void endCommunication(int duration) {
		_duration = duration;
	}
	
	@Override
	public int getUnits() {
		return _duration;
	}
	
	@Override	
	public String getType() {
		return _type;
	}
}
