package prr.terminals;

import prr.exceptions.DestinationIsOffException;

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
	}

	@Override
	public void becomeBusy() {
		// Empty
	}

	@Override
	public String toString() {
		return "OFF";
	}

}
