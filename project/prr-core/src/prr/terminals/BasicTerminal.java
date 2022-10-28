package prr.terminals;

import prr.client.Client;

public class BasicTerminal extends Terminal {
	
	private static final long serialVersionUID = 2887193467191521625L;
	private final static String terminalType = "BASIC";
	
	public BasicTerminal(String key, Client client) {
		super(key, client);
	}
	
	@Override
	public boolean canHandleCommunication(String communicationType) {
		switch (communicationType) {
		case "VOICE": return true;
		case "VIDEO": return false;
		default: return false;
		}
	}

	public String getType() {
		return terminalType;
	}

}
