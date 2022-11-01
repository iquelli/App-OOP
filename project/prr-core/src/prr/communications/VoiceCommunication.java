package prr.communications;

import prr.client.Level;
import prr.terminals.Terminal;

public class VoiceCommunication extends Communication {
	private final String _type = "VOICE";
	
	private int _duration;

	public VoiceCommunication(int key, Terminal sender, Terminal receiver) {
		super(key, sender, receiver);
		_duration = 0;
	}

	@Override
	public int definePrice(Level clientLevel) {
		return clientLevel.getTariff().getVoicePrice(_duration);
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