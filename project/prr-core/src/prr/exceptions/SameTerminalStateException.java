package prr.exceptions;

import prr.terminals.Terminal;

public class SameTerminalStateException extends Exception {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
    
    private Terminal.TerminalState _state;

    public SameTerminalStateException(Terminal.TerminalState state) {
    	_state = state;
    }

	public Terminal.TerminalState getState() {
		return _state;
	}

}
