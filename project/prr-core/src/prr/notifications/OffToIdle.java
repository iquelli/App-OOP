package prr.notifications;

import prr.terminals.Terminal;

public class OffToIdle extends Notification {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private String _type = "O2I";
	
	public OffToIdle(Terminal terminal) {
		super(terminal);
	}
	
	public String getType() {
		return _type;
	}
	
}
