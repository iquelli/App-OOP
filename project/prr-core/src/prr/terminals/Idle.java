package prr.terminals;

import prr.terminals.Terminal.TerminalState;

public class Idle extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	public Idle(Terminal terminal) {
		terminal.super();
	}

	@Override
	public String toString() {
		return "IDLE";
	}

	@Override
	public void turnOff() {
		setState(new Off(getTerminal(), this));
	}

	@Override
	public void turnOn() {
		setState(new Silence(getTerminal()));
	}

	@Override
	public void becomeBusy() {
		setState(new Busy(getTerminal(), this));		
	}

	@Override
	public boolean isOnState(TerminalState state) {
		return state.toString().equals(toString());
	}

}
