package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import prr.app.visitors.RenderClient;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

	private final String keyText = "clientKey";

	DoShowClient(Network receiver) {
		super(Label.SHOW_CLIENT, receiver);
		addStringField( keyText , Prompt.key());
		
		//FIXME add command fields
	}

	@Override
	protected final void execute() throws CommandException {
        try{
			RenderClient renderer = new RenderClient();
			_receiver.getClient(stringField(keyText).accept(renderer));
			_display.popup(renderer);
		} catch(prr.exceptions.UnknownClientKeyException e) {
			throw new UnknownClientKeyException(e.getKey());
		}
	}
}
