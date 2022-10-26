package prr.communications;

import prr.client.Level;
import prr.terminals.Terminal;

public abstract class Communication {
	
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
    	_wasPaid = false;
    	_finished = false;
    }

//  **************************
//  *        Balance		 *
//  **************************

	public double getPrice() {
    	return _price;
    }
    
    public abstract int definePrice(Level clientLevel);
    
    public void performPayment() {
    	_wasPaid = true;
    }
    
    public void endCommunication(int duration) {
    	_price = definePrice(_sender.getClient().getLevel());
    	_finished = true;
    }
}
