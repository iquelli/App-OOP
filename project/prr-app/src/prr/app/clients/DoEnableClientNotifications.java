package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {
	
	private String keyText = "clientKey";
	private final String change = "ENABLE";

	DoEnableClientNotifications(Network receiver) {
		super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
		addStringField( keyText , Prompt.key());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.changeClientNotifications(stringField(keyText), change);
		}
		catch(prr.exceptions.NotificationsAlreadyAtThatState e) {
			_display.popup(Message.clientNotificationsAlreadyEnabled());
		}
		catch(prr.exceptions.UnknownClientKeyException e) {
			throw new UnknownClientKeyException(e.getKey());
		}
	}
}
