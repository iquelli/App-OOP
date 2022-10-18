package prr.app;

import prr.NetworkManager;
import prr.exceptions.DuplicateClientKeyException;
import prr.exceptions.DuplicateTerminalKeyException;
import prr.exceptions.ImportFileException;
import prr.exceptions.InvalidEntryException;
import prr.exceptions.InvalidTerminalKeyException;
import prr.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.Dialog;

/**
 * Application entry-point.
 */
public class App {

	public static void main(String[] args) throws NumberFormatException, InvalidEntryException,
	 DuplicateClientKeyException, UnknownClientKeyException, InvalidTerminalKeyException, DuplicateTerminalKeyException {
		try (var ui = Dialog.UI) {
			var receiver = new NetworkManager();

			String datafile = System.getProperty("import");
			if (datafile != null) {
				try {
					receiver.importFile(datafile);
				} catch (ImportFileException e) {
					// no behavior described: just present the problem
					e.printStackTrace();
				}
			}

			(new prr.app.main.Menu(receiver)).open();
		}
	}

}