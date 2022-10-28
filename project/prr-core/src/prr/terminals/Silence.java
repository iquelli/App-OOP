package prr.terminals;

public class Silence extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	public Silence(Terminal terminal) {
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
		return false;
	}

	@Override
	public void turnOff() {
		setState(new Off(getTerminal(), this));
	}

	@Override
	public void turnOn() {
		setState(new Idle(getTerminal()));
	}

	@Override
	public void becomeBusy() {
		setState(new Busy(getTerminal(), this));
	}

	@Override
	public String toString() {
		return "SILENCE";
	}

}
