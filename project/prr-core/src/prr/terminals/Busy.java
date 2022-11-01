package prr.terminals;

import prr.client.Client;
import prr.exceptions.DestinationIsBusyException;
import prr.notifications.BusyToIdle;


public class Busy extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private Terminal.TerminalState _previousState;

	public Busy(Terminal terminal, Terminal.TerminalState previousState) {
		terminal.super();
		_previousState = previousState;
	}
	
	@Override
	public boolean canStartCommunication() {
		return false;
	}
	
	@Override
	public boolean canReceiveTextCommunication() {
		return true;
	}
	
	@Override
	public boolean canReceiveInteractiveCommunication() throws DestinationIsBusyException {
		throw new DestinationIsBusyException();
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
		
		if(!_previousState.isSilent()) 
			client.addNotification(new BusyToIdle(getTerminal()));	
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
		return "BUSY";
	}

}
