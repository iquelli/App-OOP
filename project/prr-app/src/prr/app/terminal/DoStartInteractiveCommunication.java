package prr.app.terminal;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.exceptions.CommunicationUnsupportedAtDestinationException;
import prr.exceptions.CommunicationUnsupportedAtOriginException;
import prr.exceptions.DestinationIsBusyException;
import prr.exceptions.DestinationIsOffException;
import prr.exceptions.DestinationIsSilenceException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

	private static final String DESTINATION_TERMINAL_ID_TEXT = "terminalID";
	private static final String COMMUNICATION_TYPE_TEXT = "communicationType";

	DoStartInteractiveCommunication(Network context, Terminal terminal) {
		super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
		addStringField(DESTINATION_TERMINAL_ID_TEXT, Prompt.terminalKey());
		addOptionField(COMMUNICATION_TYPE_TEXT, Prompt.commType(), "VIDEO", "VOICE");
	}

	@Override
	protected final void execute() throws CommandException {
		String destinationTerminalID = stringField(DESTINATION_TERMINAL_ID_TEXT);
		String communicationType = optionField(COMMUNICATION_TYPE_TEXT);
		
		try {
			_receiver.sendInteractiveCommunication(destinationTerminalID, communicationType, _network);			
		} catch (prr.exceptions.UnknownTerminalKeyException e) {
			throw new UnknownTerminalKeyException(destinationTerminalID);
		} catch (DestinationIsOffException e) {
			_display.popup(Message.destinationIsOff(destinationTerminalID));
		} catch (DestinationIsBusyException e) {
			_display.popup(Message.destinationIsBusy(destinationTerminalID));
		} catch (DestinationIsSilenceException e) {
			_display.popup(Message.destinationIsSilent(destinationTerminalID));
		} catch (CommunicationUnsupportedAtOriginException e) {
			_display.popup(Message.unsupportedAtOrigin(_receiver.getTerminalKey(), communicationType));
		} catch (CommunicationUnsupportedAtDestinationException e) {
			_display.popup(Message.unsupportedAtDestination(destinationTerminalID, communicationType));
		}
	}
}
