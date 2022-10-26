package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import prr.app.exceptions.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {
	
	private static final String DESTINATION_TERMINAL_ID_TEXT = "terminalID";
	private static final String COMMUNICATION_MESSAGE_TEXT = "communicationMessage";

    DoSendTextCommunication(Network context, Terminal terminal) {
        super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    }

    @Override
    protected final void execute() throws CommandException {
    	addStringField(DESTINATION_TERMINAL_ID_TEXT, Prompt.terminalKey());
    	addStringField(COMMUNICATION_MESSAGE_TEXT, Prompt.textMessage());
    	
    	String destinationTerminalID = stringField(DESTINATION_TERMINAL_ID_TEXT);
    	String communicationMessage = stringField(COMMUNICATION_MESSAGE_TEXT);
    	
    	try {
			if (!_network.canReceiveTextCommunications(destinationTerminalID)) {
				_display.popup(Message.destinationIsOff(destinationTerminalID));
				return;
			}
			
			Terminal destinationTerminal = _network.getTerminal(destinationTerminalID);
			_receiver.sendTextCommunication(destinationTerminal, communicationMessage, _network.getCommunicationsAmount());
			
		} catch (prr.exceptions.UnknownTerminalKeyException e) {
			throw new UnknownTerminalKeyException(destinationTerminalID);
		}
    }
} 
