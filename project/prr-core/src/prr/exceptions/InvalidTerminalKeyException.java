package prr.exceptions;

public class InvalidTerminalKeyException extends Exception {

	private static final long serialVersionUID = 202208091753L;

    private String _args[];

    public InvalidTerminalKeyException(String[] args) {
        _args = args;
    }

}
