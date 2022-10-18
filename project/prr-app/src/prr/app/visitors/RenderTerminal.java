package prr.app.visitors;

import prr.visits.Visitor;
import prr.terminals.Terminal;

import java.util.StringJoiner;

public class RenderTerminal implements Visitor<String> {
    
    private String textToPresent = "";

    @Override
    public String visit(Terminal terminal) {
        StringJoiner text = new StringJoiner("|");
        //FIXME acrescentar aqui os parametros (igual ao do client)
        text.add(terminal.getType);
        text.add(terminal.getTerminalKey());
        text.add(terminal.getClientKey());
        textToPresent += text;
        return textToPresent;
    }
}
