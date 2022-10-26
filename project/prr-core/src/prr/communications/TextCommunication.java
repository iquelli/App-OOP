package prr.communications;

import java.util.ArrayList;
import java.util.List;

import prr.client.Level;
import prr.terminals.Terminal;

public class TextCommunication extends Communication {
	
	private static final String _type = "TEXT";
	
	private String _message;
	
	public TextCommunication(int key, Terminal sender, Terminal receiver, String message) {
		super(key, sender, receiver);
		_message = message;
		super.endCommunication(0);
	}
	
	@Override
	public int definePrice(Level clientLevel) {
		int length = _message.length();
		
		List<Integer> prices = new ArrayList<Integer>(); // FIXME (tiago) Obter a lista dos valores que se pode cobrar com x plano tarifario
		
		if (length < 50) {
			return prices.get(0);
		}
		
		if (length < 100) {
			return prices.get(1);
		}
		
		return prices.get(2);		
	}
	
	public String getType() {
		return _type;
	}
}

