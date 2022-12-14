package prr.exceptions;

import java.io.Serial;

/**
 * Class for representing a duplicate client key problem.
 */
public class DuplicateTerminalKeyException  extends Exception {

	  /** Class serial number.*/
    @Serial
	private static final long serialVersionUID = 202208091753L;

    private final String _key;

    public DuplicateTerminalKeyException(String key) {
        _key = key;
    }
}