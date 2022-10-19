package prr.client;

import java.io.Serializable;
import java.io.Serial;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import prr.terminals.Terminal;
import prr.util.KeyComparator;
import prr.visits.Visitor;
import prr.visits.Visitable;

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
	private Map<String, Terminal> _terminals = new TreeMap<>(new KeyComparator());
    // FIX ME ainda falta métodos por definir (tipo de comunicação)

    public Client(String key, String name, int taxId) {
        _key = key;
        _name = name;
        _taxId = taxId;
        _level = new Level(); // normal level
    }

    public String getKey() {
        return _key;
    }

    public String getName() {
        return _name;
    }

    public int getTaxId() {
        return _taxId;
    }
    
    public int getRoundedPayments() {
        return (int)Math.round(_payments);
    }
    
    public int getRoundedDebts() {
        return (int)Math.round(_debts);
    }

    public boolean allowNotifications() {
        return _allowNotifications;
    }

    public String getLevelName() {
        return _level.getLevel();
    }

	public List<Terminal> getTerminals() {
		return _terminals.values().stream().collect(Collectors.toList());
	}
	
	public int getAmountOfTerminals() {
		return _terminals.size();
	}

    public void addTerminal(Terminal terminal) {
        _terminals.put(terminal.getTerminalKey(), terminal);
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

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

}