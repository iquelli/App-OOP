package prr.client;

import java.io.Serial;

import prr.tariffs.GoldTariff;
import prr.tariffs.Tariff;

public class GoldLevel extends Client.Level{
    
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    
    private Tariff _tariff = new GoldTariff();
    
    public GoldLevel(Client client) {
    	client.super();
    }

    /**
     * Obtains the client's level in string.
     * 
     * @return String clients' level in string format
     */
    @Override
    public String getLevel() {
        return "GOLD";
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
		// already Gold
		
	}

	@Override
	public void becomePlat() {
		setLevel(new PlatinumLevel(getClient()));
		
	}
    
}
