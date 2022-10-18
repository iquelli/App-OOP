package prr.app.clients;

import java.util.PropertyPermission;

import prr.Network;
import prr.app.exceptions.DuplicateClientKeyException;
import prr.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.exceptions.DuplicateClientKeyException;

//FIXME add more imports if needed

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
                //FIXME add command fields
	}

	@Override
	protected final void execute() throws CommandException {
        String clientKey = stringField(keyText);
		String clientName = stringField(nameText);
		int clientTaxId = integerField(taxIdText);

		try {
			_receiver.registerClient(clientKey, clientName, clientTaxId);

		}
		catch(prr.exceptions.DuplicateClientKeyException e) {
			throw new DuplicateClientKeyException(clientKey);
		}
	}

}
