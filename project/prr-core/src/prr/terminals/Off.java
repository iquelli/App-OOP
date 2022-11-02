package prr.terminals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import prr.client.Client;
import prr.exceptions.DestinationIsOffException;
import prr.notifications.OffToIdle;
import prr.notifications.OffToSilent;

public class Off extends Terminal.TerminalState {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private Terminal.TerminalState _previousState;
	private List<Terminal> terminalsThatAttemptedComm = new ArrayList<Terminal>();
	
	public Off(Terminal terminal, Terminal.TerminalState previousState) {
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
	public boolean canReceiveTextCommunication(Terminal terminal) throws DestinationIsOffException {
		if(!terminalsThatAttemptedComm.contains(terminal))
			terminalsThatAttemptedComm.add(terminal);
		throw new DestinationIsOffException();
	}
	
	@Override
	public boolean canReceiveInteractiveCommunication(Terminal terminal) throws DestinationIsOffException {
		if(!terminalsThatAttemptedComm.contains(terminal))
			terminalsThatAttemptedComm.add(terminal);
		throw new DestinationIsOffException();
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
			if(_previousState.isSilent()) {
				client.addNotification(new OffToSilent(getTerminal()));	
			}
			client.addNotification(new OffToIdle(getTerminal()));
		}
		
		terminalsThatAttemptedComm.clear();
	}

	@Override
	public void becomeBusy() {
		// Empty
	}
	
	public boolean isSilent() {
		return false;
	}
	

	@Override
	public String toString() {
		return "OFF";
	}

}
