package prr.exceptions;

import java.io.Serial;

public class UnknownClientKeyException extends Client {

    /** Class serial number.*/
    @Serial
	private static final long serialVersionUID = 202208091753L;
    
    private String _key;

    public UnknownClientKeyException(String key) {
        _key = key;
    }

}