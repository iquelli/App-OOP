package prr.app.lookups;

import prr.Network;
import prr.app.Render;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

	DoShowAllCommunications(Network receiver) {
		super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Render renderer = new Render();
		_receiver.getCommunications().stream().map(v -> v.accept(renderer)).forEach(_display::popup);
	}
}
