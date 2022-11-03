package prr.client;

import java.io.Serial;

import prr.tariffs.PlatTariff;
import prr.tariffs.Tariff;

public class PlatinumLevel extends Client.Level{
    
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    
    private Tariff _tariff = new PlatTariff();
    
    public PlatinumLevel(Client client) {
    	client.super();
    }

    /**
     * Obtains the client's level in string.
     * 
     * @return String clients' level in string format
     */
    @Override
    public String getLevel() {
        return "PLATINUM";
    }
    
    public Tariff getTariff() {
    	return _tariff;
    }

	@Override
	public void becomeNormal() {
		setLevel(new NormalLevel(getClient()));
		
	}

	@Override
	public void becomeGold() {
		setLevel(new GoldLevel(getClient()));
	}

	@Override
	public void becomePlat() {
		// already Platinum
		
	}
}
