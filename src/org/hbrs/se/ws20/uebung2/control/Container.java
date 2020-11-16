package src.org.hbrs.se.ws20.uebung2.control;

import java.util.ArrayList;
import java.util.List;

public class Container {

    public List<Member> aList = new ArrayList<>();

    public void addMember(Member member) throws ContainerException {
        /*
          ist das übergebene Member Objekt bereits in dem Container vorhanden? (Überprüfen)
          wenn ja, dann throw Exception vom Typ ContainerException
         */
        if (contains(member)) {
            ContainerException exception = new ContainerException();
            exception.setExceptionID(member.getID());
            throw exception;
        }
        aList.add(member);
    }

    public boolean contains(Member member) { //Prüft ob die Liste aList den übergebenen Member beinhaltet und gibt einen Boolean zurück
        Integer id = member.getID();
        for(Member x : aList) {
            if(member.getID().equals(x.getID())){
                return true;
            }
        }
        return false;
    }

    public String deleteMember(Integer id) {
        /*
        Welche Nachteile ergeben sich aus ihrer Sicht für ein solchen Fehlerhandling gegenüber einer Lösung mit Exceptions? Kurzes Statement!
            Exception ermöglichen es uns Fehler und Abstürze im Programmablauf sicher zu behandeln. Exceptions werden weiter gegeben bis Sie von uns behandelt oder ausgegeben werden.
            In diesem Fall wird keine Exception(Fehlermeldung) erzeugt, sondern lediglich ein String zurückgegeben. Das heißt auch das das Programm weiter läuft obwohl möglicherweise
            ein Kritischer Fehler aufgetaucht ist.
         */
        Member x = getMember(id);
        if(x == null) {
            return "Member ist nicht in der Liste enthalten!";
        } else {
            aList.remove(x);
            return "Der Member mit der ID: " + id + " wurde erfolgreich gelöscht!";
        }
    }

    private Member getMember(Integer id) { //Gibt den Member mit der übergebenen ID zurück oder wenn er nicht vorhanden ist null.
        for(Member x:aList) {
            if(id.equals(x.getID())){
                return x;
            }
        }
        return null;
    }

    public void dump() { //Ausgabe aller IDs der vorhandenen Member in der Console
        System.out.print("Alle IDs der aktuell abgespeicherten Objekte im Container:\n");
        for(Member x:aList){
            System.out.print(x.toString());
        }
    }

    public int size() { //Anzahl der Member in aList
        return aList.size();
    }
}
