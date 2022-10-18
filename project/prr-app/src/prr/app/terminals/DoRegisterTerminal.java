package prr.app.terminals;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.app.exceptions.DuplicateTerminalKeyException;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {
	
	private static final String keyText = "terminalId";
	private static final String clientKeyText = "terminalClientKey";
	private static final String typeText = "terminalType";

	DoRegisterTerminal(Network receiver) {
		super(Label.REGISTER_TERMINAL, receiver);
		addStringField(keyText, Prompt.terminalKey());
		addStringField(clientKeyText, Prompt.clientKey());
		addOptionField(typeText, Prompt.terminalType(), "BASIC", "FANCY");
		//FIXME add command fields
	}

	@Override
	protected final void execute() throws CommandException {
		String terminalKey = stringField(keyText);
		String clientKey = stringField(clientKeyText);
		String type = optionField(typeText);
		
		try {
			_receiver.registerTerminal(terminalKey, clientKey, type);
		}
		catch (prr.exceptions.InvalidTerminalKeyException e) {
			throw new UnknownTerminalKeyException(terminalKey);
		} 
		catch (prr.exceptions.UnknownClientKeyException e) {
			throw new UnknownClientKeyException(clientKey);
		}
		catch (prr.exceptions.DuplicateTerminalKeyException e) {
			throw new DuplicateTerminalKeyException(clientKey);
		}
	}
}
