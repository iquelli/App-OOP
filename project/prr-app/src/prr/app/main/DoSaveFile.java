package prr.app.main;

import prr.NetworkManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;
//FIXME add more imports if needed

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

	DoSaveFile(NetworkManager receiver) {
		super(Label.SAVE_FILE, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			try {
				_receiver.save();
			} 
			catch(prr.exceptions.MissingFileAssociationException e) {
				// Empty
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
