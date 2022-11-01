package prr.tariffs;

public class PlatTariff extends Tariff {
	
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

	@Override
	public int getMessagePrice(int n) {
		if(n < 50)
			return 0;
		return 4;
	}

	@Override
	public int getVoicePrice(int n) {
		return 10*n;
	}

	@Override
	public int getVideoPrice(int n) {
		return 10*n;
	}

}