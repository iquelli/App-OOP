package prr.exceptions;

import java.io.Serial;

public class UnknownCommunicationKeyException extends Exception {

    /** Class serial number.*/
    @Serial
	private static final long serialVersionUID = 202208091753L;
    
    private int _key;

    public UnknownCommunicationKeyException(int key) {
        _key = key;
    }

	public int getKey() {
		return _key;
	}

}
