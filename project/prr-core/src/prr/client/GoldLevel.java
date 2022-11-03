package prr.client;

import java.io.Serial;

import prr.tariffs.GoldTariff;
import prr.tariffs.Tariff;

public class GoldLevel extends Client.Level{
    
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    
    private Tariff _tariff = new GoldTariff();
    private int _consecutiveCommunications;
    
    public GoldLevel(Client client) {
    	client.super();
    	_consecutiveCommunications = 0;
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
	public void updateLevel() {
		if (getClient().getBalance() < 0) {
			setLevel(new NormalLevel(getClient()));
			return;
		}
		
		if (_consecutiveCommunications == 5) {
			setLevel(new PlatinumLevel(getClient()));
		}
	}
	
	@Override
	public void madeVideoCommunication() {
		_consecutiveCommunications++;
	}

	@Override
	public void madeVoiceCommunication() {
		_consecutiveCommunications = 0;	
	}

	@Override
	public void madeTextCommunication() {
		_consecutiveCommunications = 0;	
	}
    
}
