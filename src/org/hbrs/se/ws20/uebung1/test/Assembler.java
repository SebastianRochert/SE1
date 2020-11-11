package org.hbrs.se.ws20.uebung1.test;

import org.hbrs.se.ws20.uebung1.control.GermanTranslator;
import org.hbrs.se.ws20.uebung1.control.Translator;
import org.hbrs.se.ws20.uebung1.view.Client;

public class Assembler {
    private Client client = null;

    /**
     * Anwendung Dependency Injection (DI)
     * Anwendung u.a. Spring
     */
    public Assembler() {
        Translator german = new GermanTranslator();
    //    Client client = new Client(german);

        client.display(1);
        // Deutsch

    //    client.setTranslator(new GermanTranslator()); //eigentlich EnglishTranslator
        client.display(2);
        // Englisch
    }

}
