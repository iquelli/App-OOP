package prr.app.visitors;

import prr.visits.Visitor;
import prr.client.Client;
import prr.terminals.Terminal;

import java.util.StringJoiner;

public class RenderClient extends Visitor<String> {
    
    private String textToPresent = "";

    @Override
    public String visit(Client client) {
        StringJoiner text = new StringJoiner("|");
        text.add("CLIENT");
        text.add(client.getKey());
        text.add(Integer.toString(client.getTaxId()));

        if(client.getNotif()) 
            text.add("YES");
        else
            text.add("NO");
        //FIXME FALTAM OS OUTROS VALORES

        textToPresent += text;

        return textToPresent;
    }

    @Override
    public String visit(Terminal terminal) {
        // TODO Auto-generated method stub
        return null;
    }
}
