package prr.client;

import java.io.Serial;

public class GoldLevel extends Level{
    
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /**
     * Obtains the client's level in string.
     * 
     * @return String clients' level in string format
     */
    @Override
    public String getLevel() {
        return "GOLD";
    }
}
