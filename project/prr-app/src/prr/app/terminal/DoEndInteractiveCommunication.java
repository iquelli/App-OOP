package prr.app.terminal;

import prr.Network;
import prr.exceptions.SameTerminalStateException;
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
		addIntegerField(COMMUNICATION_DURATION_TEXT, Prompt.duration());
	}

	@Override
	protected final void execute() throws CommandException {
		int duration = integerField(COMMUNICATION_DURATION_TEXT);
		
		try {
			long cost = _receiver.endInteractiveCommunication(duration);
			_display.popup(Message.communicationCost(cost));
		} catch (SameTerminalStateException e) {
			// Empty
		}
	}
}
