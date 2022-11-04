package prr.app.main;

import prr.NetworkManager;
import prr.app.exceptions.FileOpenFailedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {
	
	private static final String fileNameText = "fileName";

	DoOpenFile(NetworkManager receiver) {
		super(Label.OPEN_FILE, receiver);
		addStringField(fileNameText, Prompt.openFile());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.load(stringField(fileNameText));
        } catch (prr.exceptions.UnavailableFileException e) {
        	throw new FileOpenFailedException(e);
        }
	}
}
