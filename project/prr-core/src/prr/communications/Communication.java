package prr.communications;

public class Communication {
	
    private String _key;
    private String _senderId;
    private String _receiverId;
    private int _units;
    private double _price;
    private boolean _wasPaid;
    private boolean _status;
    
    public Communication(String key, String senderId, String receiverId, int units, double price, boolean status) {
    	_key = key;
    	_senderId = senderId;
    	_receiverId = receiverId;
    	_units = units;
    	_price = price;
    	_wasPaid = false;
    	_status = status;
    }

//  **************************
//  *        Balance		 *
//  **************************
    
    public double getPrice() {
    	return _price;
    }
    
    public void performPayment() {
    	_wasPaid = true;
    }
}
