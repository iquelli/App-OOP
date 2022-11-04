package prr.app.lookups;

import prr.Network;
import prr.app.Render;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {
	
	private static final String CLIENT_KEY_TEXT = "clientKey";

	DoShowCommunicationsFromClient(Network receiver) {
		super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
		addStringField(CLIENT_KEY_TEXT, Prompt.clientKey());
	}

	@Override
	protected final void execute() throws CommandException {
		String clientKey = stringField(CLIENT_KEY_TEXT);
		Render renderer = new Render();
		try {
			_receiver.getCommunicationsFromClient(clientKey).stream().map(v -> v.accept(renderer)).forEach(_display::popup);
		} catch (prr.exceptions.UnknownClientKeyException e) {
			throw new UnknownClientKeyException(e.getKey());
		}
	}
}
