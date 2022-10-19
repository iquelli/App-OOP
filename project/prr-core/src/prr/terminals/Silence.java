package prr.terminals;

import prr.terminals.Terminal.TerminalState;

public class Silence extends Terminal.TerminalState {
	
	public Silence(Terminal terminal) {
		terminal.super();
	}

	@Override
	public String toString() {
		return "SILENCE";
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void becomeIdle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void silence() {
		// TODO Auto-generated method stub
		
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
