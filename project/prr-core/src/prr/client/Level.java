package prr.client;

import java.io.Serial;
import java.io.Serializable;

public class Level implements Serializable {
    
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /**
     * Obtains the client's level in string.
     * 
     * @return String clients' level in string format
     */
    public String getLevel() {
        return "NORMAL";
    }
}
