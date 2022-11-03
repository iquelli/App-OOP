package prr.terminals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import prr.client.Client;
import prr.exceptions.DestinationIsBusyException;
import prr.notifications.BusyToIdle;


public class Busy extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private Terminal.TerminalState _previousState;
	private List<Terminal> terminalsThatAttemptedComm = new ArrayList<Terminal>();

	public Busy(Terminal terminal, Terminal.TerminalState previousState) {
		terminal.super();
		_previousState = previousState;
		terminalsThatAttemptedComm = _previousState.getTerminalsThatAttemptedComm().stream().collect(Collectors.toList());
	}
	
	@Override
	public List<Terminal> getTerminalsThatAttemptedComm() {
		return terminalsThatAttemptedComm;
	}
	
	@Override
	public boolean canStartCommunication() {
		return false;
	}
	
	@Override
	public boolean canReceiveTextCommunication(Terminal terminal) {
		return true;
	}
	
	@Override
	public boolean canReceiveInteractiveCommunication(Terminal terminal) throws DestinationIsBusyException {
		if(!terminalsThatAttemptedComm.contains(terminal))
			terminalsThatAttemptedComm.add(terminal);
		throw new DestinationIsBusyException();
	}

	@Override
	public void turnOff() {
		// Empty
	}

	@Override
	public void turnOn() {
		setState(_previousState);
		
		// creates notification
		for(Terminal terminal : terminalsThatAttemptedComm) {
			Client client = terminal.getClient();
			if(!_previousState.isSilent())
				client.addNotification(new BusyToIdle(getTerminal()));	
		}
		
		terminalsThatAttemptedComm.clear();
	}

	@Override
	public void becomeBusy() {
		// Empty		
	}
	
	@Override
	public void becomeSilent() {
		// Empty
	}
	
	public boolean isSilent() {
		return false;
	}
	
	@Override
	public String toString() {
		return "BUSY";
	}

}
