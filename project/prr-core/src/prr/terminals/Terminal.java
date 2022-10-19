package prr.terminals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import prr.client.Client;
import prr.communications.Communication;
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
	private List<Communication> _communications;
	private double _payments;
	private double _debts;
	private List<String> _friends;
//	private List<Notification> _notificationsToBeSend;
//	private InteractiveCommunication _communicationOngoing;
	
	public Terminal(String key, Client client) {
		_key = key;
		_client = client;
		_state = new Idle(this);
		_communications = new ArrayList<Communication>();
		_payments = 0.0;
		_debts = 0.0;
		_friends = new ArrayList<String>();
	}

    public String getTerminalKey() {
        return _key;
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
            // FIXME add implementation code
			return false;
    }

//  **************************
//  *          State		 *
//  **************************
    
    public abstract class TerminalState {
    	
    	protected void setState(TerminalState state) {
    		_state = state;
    	}
    	
    	protected Terminal getTerminal() {
    		return Terminal.this;
    	}
    	
    	public abstract void turnOff();
    	public abstract void becomeIdle();
    	public abstract void silence();
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

//  **************************
//  *     Communications	 *
//  **************************
    
    public List<Communication> getPastCommunications() {
    	return _communications;
    }
    
    public abstract String getType();

//  **************************
//  *         Friends	     *
//  **************************
        
    public void addFriend(String friendKey) {
    	if (_friends.contains(friendKey)) {
    		return;
    	}
    	
    	_friends.add(friendKey);
    }
    
    public boolean isFriendWith(String terminalKey) {    	
    	return _friends.contains(terminalKey);
    }
    
    public String getFriends() {
    	if (_friends.size() == 0) {
    		return null;
    	}
    	
    	StringJoiner friends = new StringJoiner(", ");
    	for (String friendKey : _friends) {
    		friends.add(friendKey);
    	}
    	
    	return friends.toString();
    }
    
    public void endInteractiveCommunication(int duration) {
    	
    }
    
    public void performPayment(int communicationKey) {
    	
    }
    
    public void removeFriend(String terminalKey) {
    	
    }
    
    public void sendTextCommunication(String terminalToKey, String message) {
    	
    }
    
    public String showOngoingCommunication() {
    	return null;
    }
    
    public String showTerminalBalance() {
    	return null;
    }
    
    public void idleTerminal() {
    	
    }
    
    public void silenceTerminal() {
    	
    }
    
    public void startInteractiveCommunication(String terminalToKey, String communicationType) {
    	
    }
    
    public void turnOffTerminal() {
    	
    }
    
    public void turnOnTerminal() {
    	
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
    
}
