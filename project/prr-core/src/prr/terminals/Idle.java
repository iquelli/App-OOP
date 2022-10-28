package prr.terminals;

public class Idle extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	public Idle(Terminal terminal) {
		terminal.super();
	}
	
	@Override
	public boolean canStartCommunication() {
		return true;
	}
	
	@Override
	public boolean canReceiveTextCommunication() {
		return true;
	}
	
	@Override
	public boolean canReceiveInteractiveCommunication() {
		return true;
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
	public String toString() {
		return "IDLE";
	}

}
