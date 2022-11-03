package prr.client;

import java.io.Serial;

import prr.tariffs.PlatTariff;
import prr.tariffs.Tariff;

public class PlatinumLevel extends Client.Level{
    
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    
    private Tariff _tariff = new PlatTariff();
	private int _consecutiveCommunications;	
    
    public PlatinumLevel(Client client) {
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
        return "PLATINUM";
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
		
		if (_consecutiveCommunications == 2) {
			setLevel(new GoldLevel(getClient()));
		}
	}
	
	@Override
	public void madeVideoCommunication() {
		_consecutiveCommunications = 0;	
	}

	@Override
	public void madeVoiceCommunication() {
		_consecutiveCommunications = 0;	
	}

	@Override
	public void madeTextCommunication() {
		_consecutiveCommunications++;	
	}
}
