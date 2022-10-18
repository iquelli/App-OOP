package prr.visits;

import prr.client.Client;

import java.io.Serializable;

public abstract class Visitor<T> implements Serializable{
    
    public abstract T visit(Client client);
}
