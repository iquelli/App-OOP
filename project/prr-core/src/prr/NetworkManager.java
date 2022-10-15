package prr;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import prr.exceptions.ImportFileException;
import prr.exceptions.MissingFileAssociationException;
import prr.exceptions.UnavailableFileException;
import prr.exceptions.UnrecognizedEntryException;

//FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Manage access to network and implement load/save operations.
 */
public class NetworkManager {

	/** The network itself. */
	private Network _network = new Network();
        //FIXME  addmore fields if needed
	
	/** The network manager. */
	private String _fileName = "";

	/**
     * @return network
     */
	public Network getNetwork() {
		return _network;
	}

	/**
     * @return String
     */
	public String getFileName() {
		return _fileName;
	}

	/**
	 * @param filename name of the file containing the serialized application's state
         *        to load.
	 * @throws UnavailableFileException if the specified file does not exist or there is
         *         an error while processing this file.
	 */
	public void load(String filename) throws UnavailableFileException {
		try (ObjectInputStream ois = new ObjectInputStream(
									 new BufferedInputStream(
									 new FileInputStream(filename)))) {
			_network = (Network) ois.readObject();
			_fileName = filename;
		} catch (IOException | ClassNotFoundException e) {
			throw new UnavailableFileException(filename);
		}
	}

	/**
         * Saves the serialized application's state into the file associated to the current network.
         *
	 * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
	 * @throws MissingFileAssociationException if the current network does not have a file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 */
	public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
		if(_fileName == null || _fileName.isBlank())
			throw new MissingFileAssociationException();
		try (ObjectOutputStream oos = new ObjectOutputStream(
									  new BufferedOutputStream(
									  new FileOutputStream(_fileName)))) {
			oos.writeObject(_network);
		}
	}

	/**
         * Saves the serialized application's state into the specified file. The current network is
         * associated to this file.
         *
	 * @param filename the name of the file.
	 * @throws FileNotFoundException if for some reason the file cannot be created or opened.
	 * @throws MissingFileAssociationException if the current network does not have a file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 */
	public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
		_fileName = filename;
		save();
	}

	/**
	 * Read text input file and create domain entities..
	 * 
	 * @param filename name of the text input file
	 * @throws ImportFileException
	 */
	public void importFile(String filename) throws ImportFileException {
		try {
			_network.importFile(filename);
		} catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
			throw new ImportFileException(filename, e);
		}
	}
	
	/**
	 * Resets the network.
	 */
	public void reset() {
		_network = new Network();
		_fileName = null;
	}
}
