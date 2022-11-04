package prr.terminals;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import prr.Network;
import prr.client.Client;
import prr.communications.Communication;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.exceptions.CommunicationUnsupportedAtDestinationException;
import prr.exceptions.CommunicationUnsupportedAtOriginException;
import prr.exceptions.DestinationIsBusyException;
import prr.exceptions.DestinationIsOffException;
import prr.exceptions.DestinationIsSilenceException;
import prr.exceptions.NoOngoingCommunicationException;
import prr.exceptions.SameTerminalStateException;
import prr.exceptions.UnknownCommunicationKeyException;
import prr.exceptions.UnknownTerminalKeyException;
import prr.util.TerminalKeyComparator;
import prr.visits.Visitable;
import prr.visits.Visitor;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Visitable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	private String _key;
	private Client _client;
	private TerminalState _state;
	private Map<Integer, Communication> _communications;
	private double _payments;
	private double _debts;
	private Map<String, Terminal> _friends;
	private Communication _ongoingCommunication;
	
	public Terminal(String key, Client client) {
		_key = key;
		_client = client;
		_state = new Idle(this);
		_communications = new TreeMap<Integer, Communication>();
		_payments = 0.0;
		_debts = 0.0;
		_friends = new TreeMap<String, Terminal>(new TerminalKeyComparator());
	}
	
//  **************************
//  *        General		 *
//  **************************

    public String getTerminalKey() {
        return _key;
    }
    
    public Client getClient() {
    	return _client;
    }
    
    public TerminalState getState() {
    	return _state;
    }
    
    public Communication getOngoingCommunication() throws NoOngoingCommunicationException {
    	if (_ongoingCommunication == null) {
    		throw new NoOngoingCommunicationException();
    	}
    	
    	return _ongoingCommunication;
    }

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive communication) and
     *          it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
		return _ongoingCommunication != null && _ongoingCommunication.getSender().getTerminalKey().equals(_key);
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
		return _state.canStartCommunication();
    }
    

//  **************************
//  *          State		 *
//  **************************
    
    public void turnOff() throws SameTerminalStateException {
    	_state.turnOff();
    }
    
    public void turnOn() throws SameTerminalStateException {
    	_state.turnOn();
    }
    
    public void silence() throws SameTerminalStateException {
    	_state.becomeSilent();
    }
    
    public abstract class TerminalState implements Serializable {

    	/** Serial number for serialization. */
    	private static final long serialVersionUID = 202208091753L;

		protected void setState(TerminalState state) {
    		_state = state;
    	}
    	
    	protected Terminal getTerminal() {
    		return Terminal.this;
    	}
    	
    	public abstract boolean canStartCommunication();
    	public abstract boolean canReceiveTextCommunication(Terminal terminal) throws DestinationIsOffException;
    	public abstract boolean canReceiveInteractiveCommunication(Terminal terminal) throws 
    	DestinationIsOffException, DestinationIsSilenceException, DestinationIsBusyException;
    	
    	public abstract void turnOff() throws SameTerminalStateException;
    	public abstract void turnOn() throws SameTerminalStateException;
    	public abstract void becomeBusy();
    	public abstract void becomeSilent() throws SameTerminalStateException;
    	public abstract boolean isSilent();
    	
    	@Override
    	public abstract String toString();

		protected abstract Collection<Terminal> getTerminalsThatAttemptedComm();
    }
    

//  **************************
//  *        Balance		 *
//  **************************
    
    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public double getBalance() {
    	return _payments - _debts;
    }
    
    public double getPayments() {
    	return _payments;
    }
    
    public double getDebts() {
    	return _debts;
    }
    
    public int getPaymentsRounded() {
    	return (int)Math.round(_payments);
    }
    
    public int getDebtsRounded() {
    	return (int)Math.round(_debts);
    }
    
    public void performPayment(int communicationKey) throws UnknownCommunicationKeyException {
    	Communication communication = getCommunication(communicationKey);
    	
    	double price = communication.getPrice();
    	
    	_payments += price;
    	_debts -= price;
    	
    	_client.performPayment(price);
    	communication.performPayment();
    }
	
	public void addDebt(double debt) {
		_debts += debt;
		_client.addDebt(debt);
	}

    
//  **************************
//  *     Communications	 *
//  **************************
    
    public Communication getCommunication(int communicationKey) throws UnknownCommunicationKeyException {
    	Communication communication = _communications.get(communicationKey);
    	if (communication == null) {
    		throw new UnknownCommunicationKeyException(communicationKey);
    	}
    	
    	return communication;
    }
    
    public List<Communication> getPastCommunications() {
    	return _communications.values().stream().toList();
    }
    
    public abstract boolean canHandleCommunication(String communicationType);
    
    public abstract String getType();
    

//  **************************
//  *         Friends	     *
//  **************************
        
    public void addFriend(String terminalKey, Network network) throws UnknownTerminalKeyException {
    	Terminal terminal = network.getTerminal(terminalKey);
    	
    	if (isFriendWith(terminal)) {
    		return;
    	}
    	
    	if(terminalKey.equals(_key)) {
    		return;
    	}
    	
    	_friends.put(terminalKey, terminal);
    }
    
    public void removeFriend(String friendKey) throws UnknownTerminalKeyException {
    	_friends.remove(friendKey);
    }
    
    public boolean isFriendWith(Terminal terminal) {
    	return _friends.containsKey(terminal.getTerminalKey());
    }
    
    public String getFriends() {
    	if (_friends.size() == 0) {
    		return null;
    	}
    	
    	return String.join(", ", _friends.keySet().stream().collect(Collectors.toList()));
    }

    
//  **************************
//  *   Text Communication	 *
//  **************************
    
    public void sendTextCommunication(String destinationTerminalKey, String message, Network network) throws 
    DestinationIsOffException, UnknownTerminalKeyException {
    	Terminal destinationTerminal = network.getTerminal(destinationTerminalKey);
    	
    	destinationTerminal.canReceiveTextCommunication(this);
    	
    	int communicationId = network.getCommunicationsAmount() + 1;
    	    	
    	TextCommunication textCommunication = new TextCommunication(communicationId, this, destinationTerminal, message);
    	
    	network.addCommunication(textCommunication);
    	
    	_communications.put(communicationId, textCommunication);
    	destinationTerminal.receiveTextCommunication(communicationId, textCommunication);
    }
    
    private boolean canReceiveTextCommunication(Terminal terminal) throws DestinationIsOffException {
    	return _state.canReceiveTextCommunication(terminal);
    }
    
    private void receiveTextCommunication(int communicationId, Communication communication) {
    	_communications.put(communicationId, communication);
    }

    
//  **************************
//  *   Interactive Comms    *
//  **************************
    
    public void sendInteractiveCommunication(String destinationTerminalKey, String communicationType, Network network) throws 
    DestinationIsOffException, DestinationIsBusyException, DestinationIsSilenceException, UnknownTerminalKeyException, 
    CommunicationUnsupportedAtOriginException, CommunicationUnsupportedAtDestinationException {
    	Terminal destinationTerminal = network.getTerminal(destinationTerminalKey);
    	
    	destinationTerminal.canReceiveInteractiveCommunication(this);
    	
    	int communicationId = network.getCommunicationsAmount() + 1;
    	
    	Communication com = null;
    	switch (communicationType) {
    	case "VOICE":
    		com = new VoiceCommunication(communicationId, this, destinationTerminal);
    		terminalsCanHandleCommunication(com, destinationTerminal);    		
    		break;
    	case "VIDEO": 
    		com = new VideoCommunication(communicationId, this, destinationTerminal);
    		terminalsCanHandleCommunication(com, destinationTerminal);
    		break;
    	}
    	network.addCommunication(com);
    	
    	receiveInteractiveCommunication(com);
    	destinationTerminal.receiveInteractiveCommunication(com);
    }
    
    private boolean canReceiveInteractiveCommunication(Terminal terminal) throws DestinationIsOffException, 
    DestinationIsSilenceException, DestinationIsBusyException {
    	return _state.canReceiveInteractiveCommunication(terminal);
    }
    
    // Makes sure that terminals are able to handle a certain communication type
    private void terminalsCanHandleCommunication(Communication com, Terminal destinationTerminal) throws 
    CommunicationUnsupportedAtDestinationException, CommunicationUnsupportedAtOriginException {
		if (!canHandleCommunication(com.getType())) {
			throw new CommunicationUnsupportedAtOriginException();
		}
		
		if (!destinationTerminal.canHandleCommunication(com.getType())) {
			throw new CommunicationUnsupportedAtDestinationException();
		}
    }
    
    private void receiveInteractiveCommunication(Communication communication) {
    	_ongoingCommunication = communication;
    	_state.setState(new Busy(this, _state));
    }
    
    public long endInteractiveCommunication(int duration) throws SameTerminalStateException {
    	_ongoingCommunication.endCommunication(duration);
    	long cost = (long) _ongoingCommunication.getPrice();

    	_ongoingCommunication.getReceiver().turnOn();
    	turnOn();
    	
    	_ongoingCommunication.getReceiver().saveInteractiveCommunication();
    	saveInteractiveCommunication();
    	
    	return cost;
    }
    
    private void saveInteractiveCommunication() {
    	_communications.put(_ongoingCommunication.getKey(), _ongoingCommunication);
    	_ongoingCommunication = null;
    }

    @Override
    public boolean equals(Object o) {
    	if (!(o instanceof Terminal)) {
    		return false;
    	}
    	
    	return ((Terminal)o).getTerminalKey().equals(_key);
    }
    
//  **************************
//  * 		  Visitor 	  	 *
//  **************************

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
