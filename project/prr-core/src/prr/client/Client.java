package prr.client;

import java.io.Serializable;
import java.io.Serial;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import prr.exceptions.NotificationsAlreadyAtThatState;

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
    private double _payments = 0.0;
    private double _debts = 0.0;
    private Level _level;
    private boolean _allowNotifications = true;
	private Map<String, Terminal> _terminals = new TreeMap<>();

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
