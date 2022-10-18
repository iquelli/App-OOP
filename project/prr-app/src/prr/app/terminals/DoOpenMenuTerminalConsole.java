package prr.app.terminals;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {
	
	private static final String terminalIdText = "terminalId";

	DoOpenMenuTerminalConsole(Network receiver) {
		super(Label.OPEN_MENU_TERMINAL, receiver);
		addStringField(terminalIdText, Prompt.terminalKey());
		//FIXME add command fields
	}

	@Override
	protected final void execute() throws CommandException {
        //FIXME implement command
        // create an instance of prr.app.terminal.Menu with the
        // selected Terminal
		
		String terminalKey = stringField(terminalIdText);
		
		try {
			_display.popup(_receiver.getTerminal(terminalKey));
		}
		catch (prr.exceptions.UnknownTerminalKeyException e) {
			throw new UnknownTerminalKeyException(terminalKey);
		}
	}
}
