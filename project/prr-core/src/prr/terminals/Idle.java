package prr.terminals;

import java.util.ArrayList;
import java.util.List;

import prr.exceptions.SameTerminalStateException;

public class Idle extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	private List<Terminal> terminalsThatAttemptedComm = new ArrayList<Terminal>();
	
	public Idle(Terminal terminal) {
		terminal.super();
	}
	
	@Override
	public List<Terminal> getTerminalsThatAttemptedComm() {
		return terminalsThatAttemptedComm;
	}
	
	@Override
	public boolean canStartCommunication() {
		return true;
	}
	
	@Override
	public boolean canReceiveTextCommunication(Terminal terminal) {
		return true;
	}
	
	@Override
	public boolean canReceiveInteractiveCommunication(Terminal terminal) {
		return true;
	}

	@Override
	public void turnOff() {
		setState(new Off(getTerminal(), this));
	}

	@Override
	public void turnOn() throws SameTerminalStateException {
		throw new SameTerminalStateException(this);
	}

	@Override
	public void becomeBusy() {
		setState(new Busy(getTerminal(), this));		
	}
	
	@Override
	public void becomeSilent() {
		setState(new Silence(getTerminal()));
	}
	
	public boolean isSilent() {
		return false;
	}

	@Override
	public String toString() {
		return "IDLE";
	}

}
