package prr.notifications;

import prr.terminals.Terminal;

public class BusyToIdle extends Notification {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private String _type = "B2I";
	private Terminal _sender;
	
	public BusyToIdle(Terminal terminal) {
		_sender = terminal;
	}
	
	public String getType() {
		return _type;
	}
	
	public Terminal getSender() {
		return _sender;
	}

}
