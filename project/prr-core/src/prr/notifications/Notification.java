package prr.notifications;

import java.io.Serializable;

import prr.terminals.Terminal;
import prr.visits.Visitable;
import prr.visits.Visitor;

public abstract  class Notification implements Serializable, Visitable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	private Terminal _receiver;
	private DeliveryMethod _deliveryMethod;
	
	public Notification(Terminal receiver) {
		_receiver = receiver;
		_deliveryMethod = new RegisterOnApp();
	}
	
	public Notification(Terminal receiver, DeliveryMethod deliveryMethod) {
		_receiver = receiver;
		_deliveryMethod = deliveryMethod;
	}
	
	public String getTerminalId() {
		return _receiver.getTerminalKey();
	}
	
	public Terminal getTerminal() {
		return _receiver;
	}
	
	public boolean isRegisteredOnApp() {
		return _deliveryMethod.getType().equals("RegisterOnApp");
	}
	
	public abstract String getType();
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Notification) {
			Notification notif = (Notification) other;
			return notif.getTerminal().getClient().getKey().
					equals(this.getTerminal().getClient().getKey());
		}
		return false;
	}
	
//  **************************
//  *         Visitor		 *
//  **************************
	
	@Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
