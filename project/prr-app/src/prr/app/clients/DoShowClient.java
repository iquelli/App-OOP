package prr.app.clients;

import prr.Network;
import prr.app.Render;
import prr.app.exceptions.UnknownClientKeyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

	private final String keyText = "clientKey";

	DoShowClient(Network receiver) {
		super(Label.SHOW_CLIENT, receiver);
		addStringField( keyText , Prompt.key());
	}

	@Override
	protected final void execute() throws CommandException {
        try{
			Render renderer = new Render();
			_display.popup(_receiver.getClient(stringField(keyText)).accept(renderer));
			
			_receiver.getAllNotif(stringField(keyText)).stream().map(v -> v.accept(renderer)).forEach(_display::popup);

		} catch(prr.exceptions.UnknownClientKeyException e) {
			throw new UnknownClientKeyException(e.getKey());
		}
	}
}