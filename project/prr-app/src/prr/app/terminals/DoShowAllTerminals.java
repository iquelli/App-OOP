package prr.app.terminals;

import prr.Network;
import prr.app.Render;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

	DoShowAllTerminals(Network receiver) {
		super(Label.SHOW_ALL_TERMINALS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Render renderer = new Render();
		_receiver.getTerminals().stream().map(p -> p.accept(renderer)).forEach(_display::popup);
	}
}
