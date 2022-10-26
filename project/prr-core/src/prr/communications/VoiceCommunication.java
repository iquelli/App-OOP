package prr.communications;

import prr.client.Level;
import prr.terminals.Terminal;

public class VoiceCommunication extends Communication {
	private final String _type = "VOICE";

	public VoiceCommunication(int key, Terminal sender, Terminal receiver, String message) {
		super(key, sender, receiver);
	}

	@Override
	public int definePrice(Level clientLevel) {
		return 0;
	}

	public String getType() {
		return _type;
	}
}