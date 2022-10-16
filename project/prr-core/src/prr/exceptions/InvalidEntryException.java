package prr.exceptions;

/**
 * Class for invalid entries (such as writing CLINT instead of CLIENT)
 */
public class InvalidEntryException extends Exception {
    
    /** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

    private String _args [];

    public InvalidEntryException(String [] args) {
        _args = args;
    }
}
