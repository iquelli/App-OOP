package prr.util;

import java.util.Comparator;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class serves to sort terminal keys.
 */
public class TerminalKeyComparator implements Comparator<String>, Serializable {
	
    /** Serial number for serialization. */
    @Serial
	private static final long serialVersionUID = 202208091753L;

	@Override
	public int compare(String terminal1, String terminal2) {
		int terminalKey1 = Integer.parseInt(terminal1);
		int terminalKey2 = Integer.parseInt(terminal2);
		return terminalKey1 - terminalKey2;
	}

}
