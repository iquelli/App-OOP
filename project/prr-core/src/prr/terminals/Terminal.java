package prr.terminals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

import prr.client.Client;
import prr.communications.Communication;
import prr.communications.TextCommunication;
import prr.exceptions.SameTerminalStateException;
import prr.exceptions.UnknownCommunicationKeyException;
import prr.visits.Visitable;
import prr.visits.Visitor;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Visitable /* FIXME maybe addd more interfaces */{

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	private String _key;
	private Client _client;
	private TerminalState _state;
	private Map<Integer, Communication> _communications;
	private double _payments;
	private double _debts;
	private List<Terminal> _friends;
//	private List<Notification> _notificationsToBeSend;
//	private InteractiveCommunication _communicationOngoing;
	
	public Terminal(String key, Client client) {
		_key = key;
		_client = client;
		_state = new Idle(this);
		_communications = new TreeMap<Integer, Communication>();
		_payments = 0.0;
		_debts = 0.0;
		_friends = new ArrayList<Terminal>();
	}

    public String getTerminalKey() {
        return _key;
    }
    
    public Client getClient() {
    	return _client;
    }

    public String getClientKey() {
        return _client.getKey();
    }
    
    public TerminalState getState() {
    	return _state;
    }

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive communication) and
     *          it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
            // FIXME add implementation code
			return false;
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
		return _state.canStartCommunication();
    }
    
    public boolean canReceiveTextCommunication() {
    	return _state.canReceiveTextCommunication();
    }
    
    public void sendTextCommunication(Terminal destinationTerminal, String message, int communicationId) {
    	TextCommunication textCommunication = new TextCommunication(communicationId, this, destinationTerminal, message);
    	
    	_communications.put(communicationId, textCommunication);
    	destinationTerminal.receiveCommunication(communicationId, textCommunication);
    }
    
    public void receiveCommunication(int communicationId, Communication communication) {
    	_communications.put(communicationId, communication);
    }

//  **************************
//  *          State		 *
//  **************************
    
    private void checkIfIsOnSameState(Terminal.TerminalState newState) throws SameTerminalStateException {
    	if (newState.toString().equals(_state.toString())) {
    		throw new SameTerminalStateException(newState);
    	}
    }
    
    public void turnOff() throws SameTerminalStateException {
    	checkIfIsOnSameState(new Off(this, _state));
    	_state.turnOff();
    }
    
    public void turnOn() throws SameTerminalStateException {
    	checkIfIsOnSameState(new Idle(this));
    	_state.turnOn();
    }
    
    public void silence() throws SameTerminalStateException {
    	checkIfIsOnSameState(new Silence(this));
    	_state.turnOn();
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
    	public abstract boolean canReceiveTextCommunication();
    	public abstract boolean canReceiveInteractiveCommunication();
    	
    	public abstract void turnOff();
    	public abstract void turnOn();
    	public abstract void becomeBusy();
    	
    	public abstract boolean isOnState(TerminalState state);
    	
    	@Override
    	public abstract String toString();
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
    
    public abstract String getType();

//  **************************
//  *         Friends	     *
//  **************************
        
    public void addFriend(Terminal friend) {
    	if (isFriendWith(friend.getTerminalKey())) {
    		return;
    	}
    	
    	_friends.add(friend);
    }
    
    public void removeFriend(Terminal friend) {
    	_friends.remove(friend);
    }
    
    public boolean isFriendWith(String terminalKey) {
    	for (Terminal friend : _friends) {
    		if (friend.getTerminalKey().equals(terminalKey)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public String getFriends() {
    	if (_friends.size() == 0) {
    		return null;
    	}
    	
    	StringJoiner friends = new StringJoiner(", ");
    	for (Terminal friend : _friends) {
    		friends.add(friend.getTerminalKey());
    	}
    	
    	return friends.toString();
    }
    
    public void endInteractiveCommunication(int duration) {
    	
    }
    
    public String showOngoingCommunication() {
    	return null;
    }
    
    public String showTerminalBalance() {
    	return null;
    }
    
    public void startInteractiveCommunication(String terminalToKey, String communicationType) {
    	
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
