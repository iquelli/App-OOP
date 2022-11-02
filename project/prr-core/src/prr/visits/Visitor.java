package prr.visits;

import prr.client.Client;
import prr.communications.Communication;
import prr.terminals.Terminal;

import java.io.Serializable;

public abstract class Visitor<T> implements Serializable{
    
	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	public abstract T visit(Client client);
    public abstract T visit(Terminal terminal);
	public abstract T visit(Communication communication);
}
