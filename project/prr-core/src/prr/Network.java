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
import prr.exceptions.InvalidEntryException;
import prr.exceptions.InvalidTerminalKeyException;
import prr.client.Client;
import prr.communications.Communication;
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
	
	private Map<String, Client> _clients = new TreeMap<>(new KeyComparator());
	private Map<String, Terminal> _terminals = new TreeMap<>(new KeyComparator());
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
	 * @throws InvalidEntryException 
	 * @throws DuplicateClientKeyException 
	 * @throws NumberFormatException 
	 * @throws DuplicateTerminalKeyException
	 * @throws InvalidTerminalKeyException
	 * @throws UnknownClientKeyException
	 */
	void importFile(String filename) throws UnrecognizedEntryException, IOException,
	 InvalidEntryException, NumberFormatException, DuplicateClientKeyException, UnknownClientKeyException, 
	 InvalidTerminalKeyException, DuplicateTerminalKeyException { 

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
	 * @throws DuplicateClientKeyException 
	 * @throws NumberFormatException 
	 */
	private void interpretsLine(String args[]) throws InvalidEntryException, NumberFormatException,
	 DuplicateClientKeyException, UnknownClientKeyException, InvalidTerminalKeyException , DuplicateTerminalKeyException{
		switch (args[0]) {
			case "CLIENT": evaluateClientEntry(args);
			case "BASIC" :
			case "FANCY" :
							evaluateTerminalEntry(args);
							break;
			// FIXME falta adicionar os outros casos
			
			default : throw new InvalidEntryException(args);
		}
	}
	
	/**
	 * Sees if the entry is a valid client entry.
	 * 
	 * @param args array with the input that was on the line
	 * @throws InvalidEntryException if it is not a valid client entry
	 * @throws DuplicateClientKeyException 
	 * @throws NumberFormatException 
	 */
	private void evaluateClientEntry(String args[]) throws InvalidEntryException, NumberFormatException, DuplicateClientKeyException {
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

	/**
	 * Gets a terminal by its key. 
	 *
	 *@param key  key of the terminal to get
	 *@return Terminal  terminal associated with the key
	 *@throws UnknownTerminalKeyException  when there is no terminal with the key
	 */
	public Terminal getTerminal(String key) throws UnknownTerminalKeyException {
		if (!_terminals.containsKey(key)) {
			throw new UnknownTerminalKeyException(key);
		}
		
		return _terminals.get(key);
	}
	
	/**
	 * Gets all terminals
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
			if (terminal.getBalance() > 0) {
				terminalsWithPositiveBalance.add(terminal);
			}
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
			if (terminal.getPastCommunications().size() == 0) {
				terminalsUnused.add(terminal);
			}
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
	 * @throws DuplicateTerminalKeyException if the key is already associated to an existing terminal
	 * @throws InvalidTerminalKeyException if the key does not have 6 digits
	 * @throws UnknownClientKeyException if the client key doesnt exist
	 */

	public void evaluateTerminalEntry(String args[]) throws InvalidEntryException, UnknownClientKeyException,
	 InvalidTerminalKeyException, DuplicateTerminalKeyException {
		if (args.length != 3)
			throw new InvalidEntryException(args);
		else 
			registerTerminal(args[1], args[2], args[0]);
	}

	/**
	 * Gets the global balance of registered clients.
	 *
	 * @param terminalKey  the key that will be associated to the terminal
	 * @param clientKey  the key from the user who will be the owner
	 * @throws InvalidTerminalKeyException  if the key does not have 6 digits
	 * @throws DuplicateTerminalKeyException  if the key is already associated to an existing terminal
	 * @throws UnknowClientKeyException if the client key doesnt exist
	 */
	public void registerTerminal(String terminalKey, String clientKey, String type) throws 
	 UnknownClientKeyException, InvalidTerminalKeyException, DuplicateTerminalKeyException {
		if (!isValidTerminalKey(terminalKey)) {
			throw new InvalidTerminalKeyException(terminalKey);
		}
		
		if (_terminals.containsKey(terminalKey)) {
			throw new DuplicateTerminalKeyException(terminalKey);
		}
		
		Terminal terminal = type.equals(basicTerminalText) ? 
				new BasicTerminal(terminalKey, getClient(clientKey)) : 
				new FancyTerminal(terminalKey, getClient(clientKey));
		
		_terminals.put(terminalKey, terminal);
	}

	/**
	 * Validates if a key is valid
	 *
	 * @param key  the key that will be tested
	 * @return if the key is valid 
	 */
	private boolean isValidTerminalKey(String key) {
		if (key.length() != 6) {
			return false;
		}
		
		if (!key.chars().allMatch(Character::isDigit)) {
			return false;
		}
		
		return true;
	}

}