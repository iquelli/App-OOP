package prr.terminals;

import prr.terminals.Terminal.TerminalState;

public class Idle extends Terminal.TerminalState {
	
	public Idle(Terminal terminal) {
		terminal.super();
	}

	@Override
	public String toString() {
		return "IDLE";
	}

	@Override
	public void turnOff() {
		setState(new Off(getTerminal()));
	}

	@Override
	public void becomeIdle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void silence() {
		setState(new Silence(getTerminal()));		
	}

	@Override
	public boolean isOnState(TerminalState state) {
		return state.toString().equals(toString());
	}

	@Override
	public void becomeBusy() {
		// TODO Auto-generated method stub
		
	}

}
