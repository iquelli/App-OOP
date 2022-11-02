package prr.util;

import java.util.Comparator;

import java.io.Serial;
import java.io.Serializable;


/**
 * This class serves to sort alphanumeric strings.
 */
public class ClientKeyComparator implements Comparator<String>, Serializable {

    /** Serial number for serialization. */
    @Serial
	private static final long serialVersionUID = 202208091753L;

    @Override
    public int compare(String key1, String key2) {
        return key1.compareToIgnoreCase(key2);
    }
}