package prr.app.clients;

import prr.Network;
import prr.app.exceptions.DuplicateClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

	private static final String keyText = "clientKey";
	private static final String nameText = "clientName";
	private static final String taxIdText = "taxId";

	DoRegisterClient(Network receiver) {
		super(Label.REGISTER_CLIENT, receiver);
		addStringField(keyText, Prompt.key());
		addStringField(nameText, Prompt.name());
		addIntegerField(taxIdText, Prompt.taxId());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
	        String clientKey = stringField(keyText);
			String clientName = stringField(nameText);
			int clientTaxId = integerField(taxIdText);
						
			_receiver.registerClient(clientKey, clientName, clientTaxId);
		}
		catch(prr.exceptions.DuplicateClientKeyException e) {
			throw new DuplicateClientKeyException(e.getKey());
		}
	}

}
