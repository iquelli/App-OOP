package prr.communications;

import prr.client.Level;
import prr.terminals.Terminal;
import prr.visits.Visitable;
import prr.visits.Visitor;

public abstract class Communication implements Visitable {
	
    private int _key;
    private Terminal _sender;
    private Terminal _receiver;
    protected double _price;
    private boolean _wasPaid;
    private boolean _finished;
    
    public Communication(int key, Terminal sender, Terminal receiver) {
    	_key = key;
    	_sender = sender;
    	_receiver = receiver;
    	_price = 0;
    	_wasPaid = false;
    	_finished = false;
    }

//  **************************
//  *        Balance		 *
//  **************************

    public int getKey() {
    	return _key;
    }
    
	public double getPrice() {
    	return _price;
    }
	
	public Terminal getSender() {
		return _sender;
	}
	
	public Terminal getReceiver() {
		return _receiver;
	}
	
	public boolean isFinished() {
		return _finished;
	}
    
    public abstract int definePrice(Level clientLevel);
    
    public void performPayment() {
    	_wasPaid = true;
    }
    
    public void endCommunication(int duration) {
    	_price = definePrice(_sender.getClient().getLevel());
    	_finished = true;
    }
    
    public abstract int getUnits();
    
    public abstract String getType();

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
