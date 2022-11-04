package prr.app.terminal;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {
	
	private static final String friendKeyText = "friendKey";

	DoRemoveFriend(Network context, Terminal terminal) {
		super(Label.REMOVE_FRIEND, context, terminal);
		addStringField(friendKeyText, Prompt.terminalKey());
	}

	@Override
	protected final void execute() throws CommandException {
		String terminalKey = stringField(friendKeyText);
		try {
			_receiver.removeFriend(terminalKey);
		} catch (prr.exceptions.UnknownTerminalKeyException e) {
			throw new UnknownTerminalKeyException(terminalKey);
		}
	}
}
