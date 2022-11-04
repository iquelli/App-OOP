package prr.app.terminals;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {
	
	private static final String terminalIdText = "terminalId";

	DoOpenMenuTerminalConsole(Network receiver) {
		super(Label.OPEN_MENU_TERMINAL, receiver);
		addStringField(terminalIdText, Prompt.terminalKey());
	}

	@Override
	protected final void execute() throws CommandException {		
		String terminalKey = stringField(terminalIdText);
		
		try {
			new prr.app.terminal.Menu(_receiver, _receiver.getTerminal(terminalKey)).open();
		}
		catch (prr.exceptions.UnknownTerminalKeyException e) {
			throw new UnknownTerminalKeyException(terminalKey);
		}
	}
}
