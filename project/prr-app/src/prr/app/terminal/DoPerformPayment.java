package prr.app.terminal;

import prr.Network;
import prr.exceptions.UnknownCommunicationKeyException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {
	
	private static final String communicationKeyText = "communicationKey";

	DoPerformPayment(Network context, Terminal terminal) {
		super(Label.PERFORM_PAYMENT, context, terminal);
		addIntegerField(communicationKeyText, Prompt.commKey());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.performPayment(integerField(communicationKeyText));
		} catch (UnknownCommunicationKeyException e) {
			_display.popup(Message.invalidCommunication());
		}
	}
}
