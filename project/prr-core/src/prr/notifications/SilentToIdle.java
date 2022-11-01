package prr.notifications;

import prr.terminals.Terminal;

public class SilentToIdle extends Notification {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private String _type = "S2I";
	
	public SilentToIdle(Terminal terminal) {
		super(terminal);
	}
	
	public String getType() {
		return _type;
	}
}
