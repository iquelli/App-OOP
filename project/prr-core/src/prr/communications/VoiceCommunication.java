package prr.communications;

import prr.client.Client.Level;
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
		int normalPrice = clientLevel.getTariff().getVoicePrice(_duration);
		return getSender().isFriendWith(getReceiver()) ? normalPrice / 2 : normalPrice;
	}
	
	@Override
	public void endCommunication(int duration) {
		_duration = duration;
		super.endCommunication(duration);
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