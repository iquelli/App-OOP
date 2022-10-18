package prr.visits;

import prr.client.Client;
import prr.terminals.Terminal;

import java.io.Serializable;

public abstract class Visitor<T> implements Serializable{
    
    public abstract String visit(Client client);
    public abstract String visit(Terminal terminal);
}
