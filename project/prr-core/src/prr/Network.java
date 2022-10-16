package prr;

import java.io.Serializable;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import prr.exceptions.DuplicateClientKeyException;
import prr.exceptions.UnknownClientKeyException;



/**
 * FIXME add more import if needed (cannot import from pt.tecnico or prr.app) */ 

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private Map<String, Client> _clients = new TreeMap<>();
	private List<Terminal> _terminals;
	private List<Communication> _communication;

    // FIXME define attributes
    // FIXME define contructor
    // FIXME define methods

	/**
	 * Read text input file and create corresponding domain entities.
	 * 
	 * @param filename name of the text input file
         * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException if there is an IO erro while processing the text file
	 */
	void importFile(String filename) throws UnrecognizedEntryException, IOException { // FIXME maybe other exceptions 
		try (BufferedReader text = new BufferedReader(new FileReader(filename))) {
			String line;
			while((line = text.readLine()) != null) {
				// a acrescentar funcoes para intepretar texto
			}
		}
	}
	

	/**
	 * Gets a client by its key. 
	 *
	 *@param key      key of the client to get
	 *@return Client  client associated with the key
	 *@throws UnknownClientKeyException when there is no client with the key
	 */
	public Client getClient(String key) throws UnknwonClientKeyException {
		Client client = _clients.get(key);
		if (client == null)
			throw new UnknwonClientKeyException(key);
		return Client;
	}

	/**
	 * Adds a client to the list of clients.
	 *
	 *@param key   key of the client
	 *@param name  name of the client
	 *@param nif   fiscal number of the client
	 *@throws DuplicateClientKeyException if the key given already exists
	 */
	public void registerClient(String key, String name, int nif) 
			throws DuplicateClientKeyException {
		if (_clients.containsKey(key))
			throw new DuplicateClientKeyException(key);
		Client client = new Client(key, name, nif);
		_clients.put(key, client);
	}


}