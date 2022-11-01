package prr.notifications;

import java.io.Serializable;

import prr.terminals.Terminal;

public  class Notification  implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	private Terminal _sender;
	
	public Notification(Terminal sender) {
		_sender = sender;
	}
	
	public Terminal getSender() {
		return _sender;
	}
}

