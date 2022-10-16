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
import prr.exceptions.UnrecognizedEntryException;
import prr.exceptions.InvalidEntryException;

import prr.client.Client;

import prr.util.KeyComparator;

/**
 * FIXME add more import if needed (cannot import from pt.tecnico or prr.app) */ 

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private Map<String, Client> _clients = new TreeMap<>(new KeyComparator());
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
				interpretsLine(line.split("|"));
			}
		}
	}

	/**
	 * Evaluates the entry and calls the right function to evaluate the arguments.
	 * 
	 * @param args array with the input that was on the line
	 * @throws InvalidEntryException if the first word of the entry does not correspond
	 * 								to any option
	 */
	private void interpretsLine(String args[]) throws InvalidEntryException{
		switch (args[0]) {
			case "CLIENT" -> evaluateClientEntry(args);
			// FIXME falta adicionar os outros casos
			
			default -> throw new InvalidEntryException(args);
		}
	}
	
	/**
	 * Sees if the entry is a valid client entry.
	 * 
	 * @param args array with the input that was on the line
	 * @throws InvalidEntryException if it is not a valid client entry
	 */
	private void evaluateClientEntry(String args[]) throws InvalidEntryException {
		if (args.length != 4)
			throw new InvalidEntryException(args);
		//FIXME falta verificar se cada entrada ta correta (ex: se args[3] eh um numero)
		else 
			registerClient(args[1], args[2], Integer.parseInt(args[3]));
	}

	/**
	 * Gets a client by its key. 
	 *
	 *@param key      key of the client to get
	 *@return client  client associated with the key
	 *@throws UnknownClientKeyException when there is no client with the key
	 */
	public Client getClient(String key) throws UnknownClientKeyException {
		Client client = _clients.get(key);
		if (client == null)
			throw new UnknownClientKeyException(key);
		return client;
	}

	/**
	 * Gets the list of clients. 
	 *
	 *@return _clients  list of all the clients
	 */
	public Map<String,Client> getAllClients() {
		return _clients;
	}

	/**
	 * Adds a client to the list of clients.
	 *
	 *@param key   key of the client
	 *@param name  name of the client
	 *@param taxId   fiscal number of the client
	 *@throws DuplicateClientKeyException if the key given already exists
	 */
	public void registerClient(String key, String name, int taxId) 
			throws DuplicateClientKeyException {
		if (_clients.containsKey(key))
			throw new DuplicateClientKeyException(key);
		Client client = new Client(key, name, taxId);
		_clients.put(key, client);
	}

}