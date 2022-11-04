package prr.app.terminal;

import prr.Network;
import prr.app.Render;
import prr.exceptions.NoOngoingCommunicationException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

	DoShowOngoingCommunication(Network context, Terminal terminal) {
		super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			Render renderer = new Render();
			_display.popup(_receiver.getOngoingCommunication().accept(renderer));
		} catch (NoOngoingCommunicationException e) {
			_display.popup(Message.noOngoingCommunication());
		}
		
	}
}
