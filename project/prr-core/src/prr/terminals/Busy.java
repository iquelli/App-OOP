package prr.terminals;

import prr.terminals.Terminal.TerminalState;

public class Busy extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private Terminal.TerminalState _previousState;

	public Busy(Terminal terminal, Terminal.TerminalState previousState) {
		terminal.super();
		_previousState = previousState;
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
	public boolean isOnState(TerminalState state) {
		return state.toString().equals(toString());
	}
	
	@Override
	public String toString() {
		return "BUSY";
	}

}
