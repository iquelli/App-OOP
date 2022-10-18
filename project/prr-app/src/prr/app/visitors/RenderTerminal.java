package prr.app.visitors;

import prr.visits.Visitor;
import prr.client.Client;
import prr.terminals.Terminal;

import java.util.StringJoiner;

public class RenderTerminal extends Visitor<String> {
    
    private String textToPresent = "";

    @Override
    public String visit(Terminal terminal) {
        StringJoiner text = new StringJoiner("|");
        //FIXME acrescentar aqui os parametros (igual ao do client)
        text.add(terminal.getType());
        text.add(terminal.getTerminalKey());
        text.add(terminal.getClientKey());
        textToPresent += text;
        return textToPresent;
    }

    @Override
    public String visit(Client client) {
        // TODO Auto-generated method stub
        return null;
    }
}
