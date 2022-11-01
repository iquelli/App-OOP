package prr.terminals;

import prr.client.Client;
import prr.exceptions.DestinationIsOffException;
import prr.notifications.OffToIdle;
import prr.notifications.OffToSilent;

public class Off extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private Terminal.TerminalState _previousState;
	
	public Off(Terminal terminal, Terminal.TerminalState previousState) {
		terminal.super();
		_previousState = previousState;
	}
	
	@Override
	public boolean canStartCommunication() {
		return false;
	}
	
	@Override
	public boolean canReceiveTextCommunication() throws DestinationIsOffException {
		throw new DestinationIsOffException();
	}
	
	@Override
	public boolean canReceiveInteractiveCommunication() throws DestinationIsOffException {
		throw new DestinationIsOffException();
	}

	@Override
	public void turnOff() {
		// Empty
	}

	@Override
	public void turnOn() {
		setState(_previousState);
		
		// creates notification
		Client client = getTerminal().getClient();
		
		if(_previousState.isSilent()) {
			client.addNotification(new OffToSilent(getTerminal()));	
		}
		client.addNotification(new OffToIdle(getTerminal()));
	}

	@Override
	public void becomeBusy() {
		// Empty
	}
	
	public boolean isSilent() {
		return false;
	}
	

	@Override
	public String toString() {
		return "OFF";
	}

}
