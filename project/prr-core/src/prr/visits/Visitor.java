package prr.visits;

import prr.client.Client;
import prr.terminals.Terminal;

import java.io.Serializable;

public abstract class Visitor<T> implements Serializable{
    
    public abstract T visit(Client client);
    public abstract T visit(Terminal terminal);
}
