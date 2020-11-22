package src.org.hbrs.se.ws20.uebung3.control;

public interface Member {

    // ID ist über einen Konstruktor einer abgeleiteten Klasse
    // explizit außerhalb der Container-Klasse zu belegen
    // --> Primärschlüssel zur Unterscheidung aller Member-Objekte
    Integer getID();

    public String toString();

}
