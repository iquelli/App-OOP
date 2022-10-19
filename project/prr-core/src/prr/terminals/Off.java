package prr.terminals;

import prr.terminals.Terminal.TerminalState;

public class Off extends Terminal.TerminalState {
	
	public Off(Terminal terminal) {
		terminal.super();
	}

	@Override
	public String toString() {
		return "OFF";
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
