package prr.terminals;

import java.util.ArrayList;
import java.util.List;

import prr.client.Client;
import prr.exceptions.DestinationIsSilenceException;
import prr.notifications.SilentToIdle;

public class Silence extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	private List<Terminal> terminalsThatAttemptedComm = new ArrayList<Terminal>();
	
	public Silence(Terminal terminal) {
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
	public boolean canReceiveInteractiveCommunication(Terminal terminal) throws DestinationIsSilenceException {
		if(!terminalsThatAttemptedComm.contains(terminal))
			terminalsThatAttemptedComm.add(terminal);
		throw new DestinationIsSilenceException();
	}

	@Override
	public void turnOff() {
		setState(new Off(getTerminal(), this));
	}

	@Override
	public void turnOn() {
		setState(new Idle(getTerminal()));
		
		// creates notification
		for(Terminal terminal : terminalsThatAttemptedComm) {
			Client client = terminal.getClient();
			client.addNotification(new SilentToIdle(getTerminal()));
		}
		
		terminalsThatAttemptedComm.clear();
	}

	@Override
	public void becomeBusy() {
		setState(new Busy(getTerminal(), this));
	}
	
	@Override
	public boolean isSilent() {
		return true;
	}
	
	@Override
	public String toString() {
		return "SILENCE";
	}

}
