package prr.client;

import java.io.Serializable;
import java.io.Serial;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.stream.Collectors;

import prr.exceptions.NotificationsAlreadyAtThatState;
import prr.notifications.Notification;
import prr.terminals.Terminal;
import prr.visits.Visitor;
import prr.visits.Visitable;
import prr.communications.Communication;

public class Client implements Serializable, Visitable{

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
	private Map<String, Terminal> _terminals = new TreeMap<>();
	private List<Notification> _notifications;

    public Client(String key, String name, int taxId) {
        _key = key;
        _name = name;
        _taxId = taxId;
        _level = new NormalLevel(); // normal level
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
    
    public String getNotifications() {
    	StringJoiner notifications = new StringJoiner("\n");
    	int comp = _notifications.size();
    	
    	for(int i = 0; i < comp; i++) {
    		notifications.add(_notifications.get(i).getType() + "|" + _notifications.get(i).getTerminalId());
    	}
    	return notifications.toString();
    }
    
    public int getAmountOfNotifications() {
    	return _notifications.size();
    }
    
    public void addNotification(Notification notif) {
    	_notifications.add(notif);
    }
    
    
//  **************************
//  *        Balance		 *
//  **************************
    
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
    }
    
    public void contractDebt(double price) {
    	_debts += price;
    }
    
    public boolean hasDebt() {
    	System.out.println(_debts != 0);
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
