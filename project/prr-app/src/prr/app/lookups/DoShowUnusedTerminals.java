package prr.app.lookups;

import prr.Network;
import prr.app.Render;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

	DoShowUnusedTerminals(Network receiver) {
		super(Label.SHOW_UNUSED_TERMINALS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Render renderer = new Render();
		_receiver.getUnusedTerminals().stream().map(p -> p.accept(renderer)).forEach(_display::popup);
	}
}
