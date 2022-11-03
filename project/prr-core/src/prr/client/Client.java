package prr.client;

import java.io.Serializable;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import prr.exceptions.NotificationsAlreadyAtThatState;
import prr.notifications.Notification;
import prr.tariffs.Tariff;
import prr.terminals.Terminal;
import prr.visits.Visitor;
import prr.visits.Visitable;

public class Client implements Serializable, Visitable {

    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    private final String _key;
    private String _name;
    private int _taxId;
    private double _payments = 0;
    private double _debts = 0;
    private Level _level;
    private boolean _allowNotifications = true;
	private Map<String, Terminal> _terminals = new TreeMap<String, Terminal>();
	private List<Notification> _notifications = new ArrayList<Notification>();
	
    public Client(String key, String name, int taxId) {
        _key = key;
        _name = name;
        _taxId = taxId;
        _level = new NormalLevel(this); // normal level
    }

    
//  **************************
//  *        General		 *
//  **************************
    
    public String getKey() {
        return _key;
    }

    public String getName() {
        return _name;
    }

    public int getTaxId() {
        return _taxId;
    }
    
    public Level getLevel() {
    	return _level;
    }

    public String getLevelName() {
        return _level.getLevel();
    }
    
    
//  **************************
//  *        Terminals	     *
//  **************************

	public List<Terminal> getTerminals() {
		return _terminals.values().stream().collect(Collectors.toList());
	}
	
	public int getAmountOfTerminals() {
		return _terminals.size();
	}

    public void addTerminal(Terminal terminal) {
        _terminals.put(terminal.getTerminalKey(), terminal);
    }
    
    
//  **************************
//  *      Notifications	 *
//  **************************
    
    public boolean canReceiveNotifications() {
    	return _allowNotifications;
    }
    
    public void enableNotifications() throws NotificationsAlreadyAtThatState {
    	if(_allowNotifications)
    		throw new NotificationsAlreadyAtThatState();
    	_allowNotifications = true;
    }
    
    public void disableNotifications() throws NotificationsAlreadyAtThatState {
    	if(!_allowNotifications)
    		throw new NotificationsAlreadyAtThatState();
    	_allowNotifications = false;
    }
    
    public List<Notification> getNotifications() {
    	return _notifications;    	
    }
    
    public void clearNotifications() {
    	_notifications.clear();
    }
    
    public void addNotification(Notification notif) {
    	if(!_notifications.contains(notif))
    		_notifications.add(notif);
    }
    
    public void madeVideoCommunication() {
    	_level.madeVideoCommunication();
    }

    public void madeVoiceCommunication() {
    	_level.madeVoiceCommunication();
    }

    public void madeTextCommunication() {
    	_level.madeTextCommunication();
    }
    
//  **************************
//  *        Balance		 *
//  **************************
    
    public void updateLevel() {
    	_level.updateLevel();
    }
    
    public abstract class Level implements Serializable {
        
        @Serial
    	/** Serial number for serialization. */
        private static final long serialVersionUID = 202208091753L;
        
        protected void setLevel(Level level) {
        	_level = level;
        }
        
        protected Client getClient() {
        	return Client.this;
        }
        
        /**
         * Obtains the client's level in string.
         * 
         * @return String clients' level in string format
         */
        public abstract String getLevel();
        public abstract Tariff getTariff();
        public abstract void updateLevel();
        public abstract void madeVideoCommunication();
        public abstract void madeVoiceCommunication();
        public abstract void madeTextCommunication();
    }

    
    public long getRoundedPayments() {
        return (long)Math.round(_payments);
    }
    
    public long getRoundedDebts() {
        return (long)Math.round(_debts);
    }
    
    public double getBalance() {
    	return _payments - _debts;
    }
    
    public void performPayment(double price) {
    	_payments += price;
    	_debts -= price;
    	
    	updateLevel();
    }
	
	public void addDebt(double debt) {
		_debts += debt;
	}
    
    public boolean hasDebt() {
    	return _debts != 0;
    }
    
//  **************************
//  *         Visitor		 *
//  **************************
    
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}
