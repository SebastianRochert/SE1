package src.org.hbrs.se.ws20.uebung3.control;

import src.org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import src.org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategy;
import src.org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private List<Member> aList;
    /*
     Es soll zur Laufzeit zugesichert werden, dass von der Klasse Container nur ein einziges Mal ein Objekt erzeugt werden kann und somit nur ein
     Objekt davon im Speicher existiert.
     */
    private static Container instance; //instance = null
    //Objekt der Klasse PersistenceStrategyStream um die dort definierten Methoden nutzen zu können
    private final PersistenceStrategy<Member> pss = new PersistenceStrategyStream<Member>();

    private Container() {
        aList = new ArrayList<Member>();
    }

    public static synchronized Container getInstance() {
        if(instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public static void deleteInstance() {
        instance = null;
    }


    //Uebung 2:
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

    private boolean contains(Member member) { //Prüft ob die Liste aList den übergebenen Member beinhaltet und gibt einen Boolean zurück
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

    public List<Member> getCurrentList() {
        return this.aList;
    }

    public int size() { //Anzahl der Member in aList
        return aList.size();
    }

    /*
    Uebung 3: Neue Methoden store() und load()
     */
    public void store() throws PersistenceException {
        pss.save(aList);
    }

    public void load() throws PersistenceException {
        aList = pss.load();
    }
}
