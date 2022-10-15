package prr;

import java.io.Serializable;
import java.util.List;
import java.io.IOException;

import prr.clients.Client;
import prr.communications.Communication;
import prr.exceptions.UnrecognizedEntryException;
import prr.terminals.Terminal;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;
	
	private List<Client> _clients;
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
	void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
		//FIXME implement method
	}
	
	public void disableClientNotifications(String key) {
		
	}
	
	public void enableClientNotifications(String key) {
		
	}
	
	public void registerClient(String key, String name, int nif) {
		
	}
	
	public List<String> showAllClients() {
		return null;
	}
	
	public String showClient(String key) {
		return null;
	}
	
	public String showClientPaymentsAndDebts(String key) {
		return null;
	}
	
	public List<String> showAllCommunications() {
		return null;
	}
	
	public List<String> showClientsWithDebts() {
		return null;
	}
	
	public List<String> showClientsWithoutDebts() {
		return null;
	}
	
	public List<String> showCommunicationsFromClient(String key) {
		return null;
	}
	
	public String showCommunicationToClient(String key) {
		return null;
	}
	
	public List<String> showTerminalsWithPositiveBalance() {
		return null;
	}
	
	public List<String> showUnusedTerminals() {
		return null;
	}
	
	public String showGlobalBalance() {
		return null;
	}
	
	public void openMenuTerminalConsole(String terminalKey) {
		
	}
	
	public void registerTerminal(String terminalKey, String terminalType, String clientKey) {
		
	}
	
	public List<String> showAllTerminals() {
		return null;
	}
}