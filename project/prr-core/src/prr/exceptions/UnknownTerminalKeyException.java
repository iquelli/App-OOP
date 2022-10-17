package prr.exceptions;

import java.io.Serial;

public class UnknownTerminalKeyException extends Exception {

    /** Class serial number.*/
    @Serial
	private static final long serialVersionUID = 202208091753L;
    
    private String _key;

    public UnknownTerminalKeyException(String key) {
        _key = key;
    }
}
