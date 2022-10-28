package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {
	
	private static final String COMMUNICATION_DURATION_TEXT = "communicationDuration";

	DoEndInteractiveCommunication(Network context, Terminal terminal) {
		super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
	}

	@Override
	protected final void execute() throws CommandException {
		addIntegerField(COMMUNICATION_DURATION_TEXT, Prompt.duration());
		
		int duration = integerField(COMMUNICATION_DURATION_TEXT);
		
		long cost = _receiver.endInteractiveCommunication(duration);
		_display.popup(Message.communicationCost(cost));
	}
}
