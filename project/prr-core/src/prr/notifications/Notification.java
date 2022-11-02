package prr.notifications;

import java.io.Serializable;

import prr.terminals.Terminal;
import prr.visits.Visitable;
import prr.visits.Visitor;

public abstract  class Notification implements Serializable, Visitable {

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
	
	
//  **************************
//  *         Visitor		 *
//  **************************
	
	@Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
