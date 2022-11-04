package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {
	
	private String keyText = "clientKey";

	DoShowClientPaymentsAndDebts(Network receiver) {
		super(Label.SHOW_CLIENT_BALANCE, receiver);
		addStringField( keyText , Prompt.key());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			 List<Long> paysAndDebts = _receiver.getClientPaymentAndDebts(stringField(keyText));
			_display.popup(Message.clientPaymentsAndDebts(stringField(keyText), paysAndDebts.get(0) , paysAndDebts.get(1)));
		}
		catch(prr.exceptions.UnknownClientKeyException e) {
			throw new UnknownClientKeyException(e.getKey());
		}
	}
}
