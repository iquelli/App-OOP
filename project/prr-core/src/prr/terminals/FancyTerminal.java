package prr.terminals;

import prr.client.Client;

public class FancyTerminal extends Terminal {

	private static final long serialVersionUID = 2887193467191521625L;
	private final static String terminalType = "FANCY";
	
	public FancyTerminal(String key, Client client) {
		super(key, client);
	}

	public String getType() {
		return terminalType;
	}
}
