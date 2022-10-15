package prr.terminals;

import java.io.Serializable;
import java.util.List;

import prr.NetworkManager;
import prr.clients.Client;
import prr.communications.Communication;
import prr.communications.InteractiveCommunication;
import prr.notifications.Notification;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	private String _key;
	private Client _client;
	private TerminalType _type;
	private TerminalState _state;
	private List<Communication> _communications;
	private double _payments;
	private double _debts;
	private List<Terminal> _friends;
	private List<Notification> _notificationsToBeSend;
	private InteractiveCommunication _communicationOngoing;

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
    
    public void addFriend(String friendKey) {
    	
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
    
}
