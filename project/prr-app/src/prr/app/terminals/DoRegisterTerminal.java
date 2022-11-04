package prr.app.terminals;

import prr.Network;
import prr.app.exceptions.DuplicateTerminalKeyException;
import prr.app.exceptions.InvalidTerminalKeyException;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

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
		addOptionField(typeText, Prompt.terminalType(), "BASIC", "FANCY");
		addStringField(clientKeyText, Prompt.clientKey());
	}

	@Override
	protected final void execute() throws CommandException {
		String terminalKey = stringField(keyText);
		String type = optionField(typeText);
		String clientKey = stringField(clientKeyText);
		
		try {
			_receiver.registerTerminal(terminalKey, clientKey, type);
		}
		catch (prr.exceptions.InvalidTerminalKeyException e) {
			throw new InvalidTerminalKeyException(terminalKey);
		} 
		catch (prr.exceptions.UnknownClientKeyException e) {
			throw new UnknownClientKeyException(clientKey);
		}
		catch (prr.exceptions.DuplicateTerminalKeyException e) {
			throw new DuplicateTerminalKeyException(terminalKey);
		}
	}
}
