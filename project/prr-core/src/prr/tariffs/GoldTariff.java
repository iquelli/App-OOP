package prr.tariffs;

public class GoldTariff extends Tariff {
	
	/** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;
    

	@Override
	public int getMessagePrice(int n) {
		if (n >= 100)
			return 2*n;
		return 10;
	}

	@Override
	public int getVoicePrice(int n) {
		return 10*n;
	}

	@Override
	public int getVideoPrice(int n) {
		return 20*n;
	}

}
