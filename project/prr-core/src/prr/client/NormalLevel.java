package prr.client;

import java.io.Serial;

import prr.tariffs.NormalTariff;
import prr.tariffs.Tariff;

public class NormalLevel extends Client.Level {
    
    @Serial
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    
    private Tariff _tariff = new NormalTariff();
    
    public NormalLevel(Client client) {
    	client.super();
    }

    /**
     * Obtains the client's level in string.
     * 
     * @return String clients' level in string format
     */
    @Override
    public String getLevel() {
        return "NORMAL";
    }
    
    public Tariff getTariff() {
    	return _tariff;
    }

	@Override
	public void updateLevel() {
    	if(getClient().getBalance() > 500) {
    		setLevel(new GoldLevel(getClient()));
    	}
	}
	
	@Override
	public void madeVideoCommunication() {
		//Empty
	}

	@Override
	public void madeVoiceCommunication() {
		//Empty		
	}

	@Override
	public void madeTextCommunication() {
		//Empty		
	}
}
