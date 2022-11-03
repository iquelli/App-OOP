package prr.communications;

import prr.client.Client.Level;
import prr.terminals.Terminal;
import prr.visits.Visitable;
import prr.visits.Visitor;

public abstract class Communication implements Visitable {
	
    private int _key;
    private Terminal _sender;
    private Terminal _receiver;
    protected double _price;
    private boolean _wasPaid = false;
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
//  *        General		 *
//  **************************

    public int getKey() {
    	return _key;
    }
	
	public Terminal getSender() {
		return _sender;
	}
	
	public Terminal getReceiver() {
		return _receiver;
	}
	
	public boolean getWasPaid() {
		return _wasPaid;
	}
	
	public boolean isFinished() {
		return _finished;
	}
    
    public abstract int getUnits();
    
    public abstract String getType();

//  **************************
//  *        Balance		 *
//  **************************
    
	public double getPrice() {
    	return _price;
    }
    
	public int getPriceRounded() {
    	return (int)Math.round(_price);
    }
	
    public abstract int definePrice(Level level);
    
    public void performPayment() {
    	_wasPaid = true;
    }
    
    public void endCommunication(int duration) {
    	_price = definePrice(_sender.getClient().getLevel());
    	_sender.addDebt(_price);
    	_sender.getClient().updateLevel();
    	_finished = true;
    }

//  **************************
//  *        Visitor		 *
//  **************************

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
