package prr.communications;

public class Communication {
    private String _key;
    private String _senderId;
    private String _receiverId;
    private int _units;
    private int _price;
    private boolean _status;
    
    public Communication(String key, String senderId, String receiverId, int units, int price, boolean status) {
    	_key = key;
    	_senderId = senderId;
    	_receiverId = receiverId;
    	_units = units;
    	_price = price;
    	_status = status;
    }
}
