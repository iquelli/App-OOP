package prr.tariffs;

public class NormalTariff extends Tariff {
	
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

	@Override
	public int getMessagePrice(int n) {
		if(n < 50)
			return 10;
		if (n >= 100)
			return 2*n;
		return 16;
	}

	@Override
	public int getVoicePrice(int n) {
		return 20*n;
	}

	@Override
	public int getVideoPrice(int n) {
		return 30*n;
	}

}