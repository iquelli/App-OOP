package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.exceptions.DestinationIsOffException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {
	
	private static final String DESTINATION_TERMINAL_ID_TEXT = "terminalID";
	private static final String COMMUNICATION_MESSAGE_TEXT = "communicationMessage";

    DoSendTextCommunication(Network context, Terminal terminal) {
        super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    	addStringField(DESTINATION_TERMINAL_ID_TEXT, Prompt.terminalKey());
    	addStringField(COMMUNICATION_MESSAGE_TEXT, Prompt.textMessage());
    }

    @Override
    protected final void execute() throws CommandException {
    	String destinationTerminalKey = stringField(DESTINATION_TERMINAL_ID_TEXT);
    	String communicationMessage = stringField(COMMUNICATION_MESSAGE_TEXT);
    	
    	try {
			_receiver.sendTextCommunication(destinationTerminalKey, communicationMessage, _network);
		} catch (prr.exceptions.UnknownTerminalKeyException e) {
			throw new UnknownTerminalKeyException(destinationTerminalKey);
		} catch (DestinationIsOffException e) {
			_display.popup(Message.destinationIsOff(destinationTerminalKey));
		}
    }
} 
