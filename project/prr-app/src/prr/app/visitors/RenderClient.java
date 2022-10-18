package prr.app.visitors;

import prr.visits.ClientVisitor;
import prr.client.Client;

import java.util.StringJoiner;

public class RenderClient implements Visitor<String> {
    
    private String textToPresent = "";

    @Override
    public void visit(Client client) {
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
    }

    @Override
    public String toString() {
        return textToPresent;
    }
}
