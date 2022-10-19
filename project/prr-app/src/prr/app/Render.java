package prr.app;

import java.util.StringJoiner;

import prr.client.Client;
import prr.terminals.Terminal;
import prr.visits.Visitor;

public class Render extends Visitor<String> {
	
	private static final long serialVersionUID = 3887261783311595141L;

	@Override
	public String visit(Client client) {
		return new StringJoiner("|")
				.add("CLIENT")
				.add(client.getKey())
				.add(client.getName())
				.add(Integer.toString(client.getTaxId()))
				.add(client.getLevelName())
				.add(client.allowNotifications() ? "YES" : "NO")
				.add(Integer.toString(client.getAmountOfTerminals()))	
				.add(Integer.toString(client.getRoundedPayments()))
				.add(Integer.toString(client.getRoundedDebts()))
				.toString();
	}

	@Override
	public String visit(Terminal terminal) {
		StringJoiner terminalString = new StringJoiner("|")
				.add(terminal.getType())
				.add(terminal.getTerminalKey())
				.add(terminal.getClientKey())
				.add(terminal.getState())
				.add(Integer.toString(terminal.getPaymentsRounded()))
				.add(Integer.toString(terminal.getDebtsRounded()));
		
		String friends = terminal.getFriends();
		if (friends != null) {
			terminalString.add(friends);
		}
		
		return terminalString.toString();
	}
}
