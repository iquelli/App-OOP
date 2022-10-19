package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

	private final static String friendKeyText = "friendKey";
	
	DoAddFriend(Network context, Terminal terminal) {
		super(Label.ADD_FRIEND, context, terminal);
		addStringField(friendKeyText, Prompt.terminalKey());
	}

	@Override
	protected final void execute() throws CommandException {
		_receiver.addFriend(stringField(friendKeyText));
	}
}
