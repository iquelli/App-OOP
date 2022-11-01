package prr.tariffs;

import java.io.Serial;
import java.io.Serializable;

public abstract class Tariff implements Serializable {
	
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
	
	public abstract int getMessagePrice(int n);
	public abstract int getVoicePrice(int n);
	public abstract int getVideoPrice(int n);

}
