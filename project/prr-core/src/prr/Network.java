package prr;

import java.io.Serializable;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import prr.exceptions.DuplicateClientKeyException;
import prr.exceptions.DuplicateTerminalKeyException;
import prr.exceptions.UnknownClientKeyException;
import prr.exceptions.UnknownTerminalKeyException;
import prr.exceptions.UnrecognizedEntryException;
import prr.terminals.BasicTerminal;
import prr.terminals.FancyTerminal;
import prr.terminals.Terminal;
import prr.exceptions.InvalidTerminalKeyException;
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
	
	private static final String basicTerminalText = "BASIC";
	
	private final Map<String, Client> _clients = new TreeMap<>(new KeyComparator());
	private final Map<String, Terminal> _terminals = new TreeMap<>(new KeyComparator());
	
	private boolean _wasModified = false;

    // FIXME define attributes
    // FIXME define constructor
    // FIXME define methods

	public boolean wasModified() {
		return _wasModified;
	}
	
	public void modified() {
		_wasModified = true;
	}
	
	public void modificationsSaved() {
		_wasModified = false;
	}
	
	/**
	 * Read text input file and create corresponding domain entities.
	 * 
	 * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException if there is an IO error while processing the text file
	 * @throws InvalidEntryException if the first word of the entry does not correspond
	 * 								to any option
	 * @throws NumberFormatException if the string doenst have numbers
	 * @throws DuplicateClientKeyException if the key given already exist
	 * @throws UnknownClientKeyException when there is no client with the key
	 * @throws InvalidTerminalKeyException if the key does not have 6 digits 
	 * @throws DuplicateTerminalKeyException if the key given already exists
	 * @throws UnknownTerminalKeyException 
	 */
	void importFile(String filename) throws UnrecognizedEntryException, IOException,
	 UnrecognizedEntryException, NumberFormatException, DuplicateClientKeyException, UnknownClientKeyException, 
	 InvalidTerminalKeyException, DuplicateTerminalKeyException, UnknownTerminalKeyException { 
		try (BufferedReader text = new BufferedReader(new FileReader(filename))) {
			String line;
			while((line = text.readLine()) != null) {
				interpretsLine(line.split("\\|"));
			}
		}
	}


	/**
	 * Evaluates the entry and calls the right function to evaluate the arguments.
	 * 
	 * @param args array with the input that was on the line
	 * @throws InvalidEntryException if the first word of the entry does not correspond
	 * 								to any option
	 * @throws NumberFormatException if the string doenst have numbers
	 * @throws DuplicateClientKeyException if the key given already exists
	 * @throws UnknownClientKeyException when there is no client with the key
	 * @throws InvalidTerminalKeyException if the key does not have 6 digits 
	 * @throws DuplicateTerminalKeyException  if the key given already exists
	 * @throws UnknownTerminalKeyException 
	 * @throws UnrecognizedEntryException 
	 */
	private void interpretsLine(String args[]) throws NumberFormatException,
	 DuplicateClientKeyException, UnknownClientKeyException, InvalidTerminalKeyException , DuplicateTerminalKeyException, UnknownTerminalKeyException, UnrecognizedEntryException{
		switch (args[0]) {
			case "CLIENT": 
				evaluateClientEntry(args);
				break;
			case "BASIC" :
			case "FANCY" :
				evaluateTerminalEntry(args);
				break;
			// FIXME falta adicionar o caso FRIENDS
			
			default : throw new UnrecognizedEntryException(args[0]);
		}
	}
	

	/**
	 * Sees if the entry is a valid client entry.
	 * 
	 * @param args array with the input that was on the line
	 * @throws InvalidEntryException if it is not a valid client entry
	 * @throws DuplicateClientKeyException if the key given already exists
	 * @throws NumberFormatException if the string doenst have numbers
	 */
	private void evaluateClientEntry(String args[]) throws
	 UnrecognizedEntryException, DuplicateClientKeyException, NumberFormatException {
		if (args.length != 4)
			throw new UnrecognizedEntryException(args[0]);
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
		if (client == null) {
			throw new UnknownClientKeyException(key);
		}
		
		return client;
	}


	/**
	 * Gets the list of clients. 
	 *
	 *@return _clients  list of all the clients
	 */
	public List<Client> getAllClients() {
		return _clients.values().stream().collect(Collectors.toList());
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
		modified();
	}


	/**
	 * Gets a terminal by its key. 
	 *
	 *@param key  key of the terminal to get
	 *@return Terminal  terminal associated with the key
	 *@throws UnknownTerminalKeyException  when there is no terminal with the key
	 */
	public Terminal getTerminal(String key) throws UnknownTerminalKeyException {
		if (!_terminals.containsKey(key))
			throw new UnknownTerminalKeyException(key);
		
		return _terminals.get(key);
	}
	

	/**
	 * Gets all terminals.
	 *
	 *@return list of all terminals
	 */	
	public List<Terminal> getTerminals() {
		return _terminals.values().stream().collect(Collectors.toList());
	}


	/**
	 * Gets all terminals with a positive balance.
	 *
	 *@return terminalsWithPositiveBalance  list of terminals with positive balance
	 */	
	public List<Terminal> getTerminalsWithPositiveBalance() {
		List<Terminal> terminalsWithPositiveBalance = new ArrayList<Terminal>();
		
		for (Terminal terminal : _terminals.values()) {
			if (terminal.getBalance() > 0)
				terminalsWithPositiveBalance.add(terminal);
		}
		
		return terminalsWithPositiveBalance;		
	}


	/**
	 * Gets all terminals that had never received or sent a communication.
	 *
	 *@return terminalsUnused  list of terminals with no past communications
	 */
	public List<Terminal> getUnusedTerminals() {
		List<Terminal> terminalsUnused = new ArrayList<Terminal>();
		
		for (Terminal terminal : _terminals.values()) {
			if (terminal.getPastCommunications().size() == 0)
				terminalsUnused.add(terminal);
		}
		
		return terminalsUnused;
	}


	/**
	 * Gets the global balance of registered clients.
	 *
	 * @return globalBalance  sum of every client balance
	 */
	public double getGlobalBalance() {
		double globalBalance = 0;
		
		for (Client client : _clients.values()) {
			globalBalance += client.getBalance();
		}
		
		return globalBalance;
	}


	/**
	 * Sees if the entry is a valid Terminal entry.
	 * 
	 * @param args array with the input that was on the line
	 * @throws InvalidEntryException if it is not a valid Terminal entry
	 * @throws DuplicateTerminalKeyException if the key is already associated 
	 *                                       to an existing terminal
	 * @throws InvalidTerminalKeyException if the key does not have 6 digits
	 * @throws UnknownClientKeyException if the client key doesnt exist
	 * @throws UnknownTerminalKeyException 
	 * @throws UnrecognizedEntryException 
	 */

	public void evaluateTerminalEntry(String args[]) throws DuplicateTerminalKeyException, 
					InvalidTerminalKeyException, UnknownClientKeyException, UnknownTerminalKeyException, UnrecognizedEntryException {
		if (args.length != 4)
			throw new UnrecognizedEntryException(args[0]);
		else 
			registerTerminal(args[1], args[2], args[0], args[3]);
	}


	/**
	 * Gets the global balance of registered clients.
	 *
	 * @param terminalKey  the key that will be associated to the terminal
	 * @param clientKey  the key from the user who will be the owner
	 * @throws InvalidTerminalKeyException  if the key does not have 6 digits
	 * @throws DuplicateTerminalKeyException  if the key is already associated 
	 *                                       to an existing terminal
	 * @throws UnknowClientKeyException if the client key doesnt exist
	 */
	public void registerTerminal(String terminalKey, String clientKey, String type) throws 
	 InvalidTerminalKeyException, DuplicateTerminalKeyException, UnknownClientKeyException {
		if (!isValidTerminalKey(terminalKey)) {
			throw new InvalidTerminalKeyException(terminalKey);
		}
		
		if (_terminals.containsKey(terminalKey)) {
			throw new DuplicateTerminalKeyException(terminalKey);
		}
		
		Client client = getClient(clientKey);		
		Terminal terminal = type.equals(basicTerminalText) ? 
				new BasicTerminal(terminalKey, client) : 
				new FancyTerminal(terminalKey, client);
		
		_terminals.put(terminalKey, terminal);
		client.addTerminal(terminal);
		modified();
	}
	
	public void registerTerminal(String terminalKey, String clientKey, String type, String state) throws 
	 InvalidTerminalKeyException, DuplicateTerminalKeyException, UnknownClientKeyException, UnknownTerminalKeyException {
		registerTerminal(terminalKey, clientKey, type);
		Terminal terminal = getTerminal(terminalKey);
		switch (state) {
		case "OFF": terminal.getState().turnOff();
		case "SILENCE": terminal.getState().silence();
		}
		
	}


	/**
	 * See if a terminal key is valid.
	 *
	 * @param key  the key that will be tested
	 * @return if the key is valid 
	 */
	private boolean isValidTerminalKey(String key) {
		if (key.length() != 6)
			return false;
		
		if (!key.chars().allMatch(Character::isDigit))
			return false;
		
		return true;
	}

}