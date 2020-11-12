package src.org.hbrs.se.ws20.uebung1.control.factory;

import src.org.hbrs.se.ws20.uebung1.control.GermanTranslator;

/**
 * Factory zur Erzeugung von konsistenten Translator-Objekten
 * Lösung: Anwendg von Factory Method Pattern
 * Problem: Inkonsistente Erzeugung von Objekten
 */

public class Factory {
    public static GermanTranslator createGermanTranslator(){
        GermanTranslator germanTranslator = new GermanTranslator();
        germanTranslator.setDate("Nov/2020");
        return germanTranslator;
    }
}
