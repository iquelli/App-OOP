package prr.app.lookups;

import prr.Network;
import prr.app.Render;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

	DoShowClientsWithDebts(Network receiver) {
		super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Render renderer = new Render();
        _receiver.getClientsWithDebt().stream().map(v -> v.accept(renderer)).forEach(_display::popup);
	}
}
