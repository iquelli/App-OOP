package prr.notifications;

import prr.terminals.Terminal;

public class BusyToIdle extends Notification {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private String _type = "B2I";

	public BusyToIdle(Terminal terminal) {
		super(terminal);
	}
	
	public String getType() {
		return _type;
	}
	

}
