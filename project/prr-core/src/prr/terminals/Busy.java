package prr.terminals;

import prr.terminals.Terminal.TerminalState;

public class Busy extends Terminal.TerminalState {

	public Busy(Terminal terminal) {
		terminal.super();
	}

	@Override
	public void turnOff() {
		// Empty
	}

	@Override
	public void becomeIdle() {
		setState(new Idle(getTerminal()));		
	}

	@Override
	public void silence() {
		// TODO Auto-generated method stub		
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
