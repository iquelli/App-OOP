package prr.client;

import java.io.Serializable;

import prr.terminals.Terminal;

import java.io.Serial;

public class Client implements Serializable{

    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    private final String _key;
    private String _name;
    private int _taxId;
    private double _payments = 0.0;
    private double _debts = 0.0;
    private Level _level;
    private boolean _allowNotifications;
    private Terminal _terminals;
    // FIX ME ainda falta métodos por definir (terminais e tipo de comunicação)

    public Client(String key, String name, int taxId) {
        _key = key;
        _name = name;
        _taxId = taxId;
        _level = new Level(); // normal level
    }
    
    public boolean canReceiveNotifications() {
    	return _allowNotifications;
    }
    
    public void enableNotifications() {
    	_allowNotifications = true;
    }
    
    public void disableNotifications() {
    	_allowNotifications = false;
    }
    
    public double getBalance() {
    	return _payments - _debts;
    }
}