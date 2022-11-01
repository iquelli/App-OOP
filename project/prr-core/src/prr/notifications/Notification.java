package prr.notifications;

import java.io.Serializable;

import prr.terminals.Terminal;

public abstract  class Notification  implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	private Terminal _receiver;
	
	public Notification(Terminal receiver) {
		_receiver = receiver;
	}
	
	public String getTerminalId() {
		return _receiver.getTerminalKey();
	}
	
	public Terminal getTerminal() {
		return _receiver;
	}
	
	public abstract String getType();
}

