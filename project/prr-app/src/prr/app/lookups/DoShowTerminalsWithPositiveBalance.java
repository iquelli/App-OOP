package prr.app.lookups;

import prr.Network;
import prr.app.Render;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

	DoShowTerminalsWithPositiveBalance(Network receiver) {
		super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Render renderer = new Render();
		_receiver.getTerminalsWithPositiveBalance().stream().map(p -> p.accept(renderer)).forEach(_display::popup);
	}
}
