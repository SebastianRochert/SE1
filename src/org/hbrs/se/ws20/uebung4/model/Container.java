package src.org.hbrs.se.ws20.uebung4.model;

import src.org.hbrs.se.ws20.uebung4.model.persistence.PersistenceException;
import src.org.hbrs.se.ws20.uebung4.model.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Container {

    private List<UserStory> aList;
    /*
     Es soll zur Laufzeit zugesichert werden, dass von der Klasse Container nur ein einziges Mal ein Objekt erzeugt werden kann und somit nur ein
     Objekt davon im Speicher existiert.
     */
    private static Container instance; //instance = null


    //Objekt der Klasse PersistenceStrategyStream um die dort definierten Methoden nutzen zu können
    private PersistenceStrategy pss = null;

    public void setPss(PersistenceStrategy<UserStory> pss) {
        this.pss = pss;
    }

    private Container() {
        aList = new ArrayList<UserStory>();
    }

    //Vorteil: Erzeugung des Objektes nur bei Bedarf
    //Anwendungsfall für Singleton Pattern:
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
    public void addUserStory(UserStory us) throws ContainerException {
        /*
          ist das übergebene Member Objekt bereits in dem Container vorhanden? (Überprüfen)
          wenn ja, dann throw Exception vom Typ ContainerException
         */
        if (contains(us)) {
            ContainerException exception = new ContainerException();
            exception.setExceptionID(us.getId());
            throw exception;
        }
        aList.add(us);
    }

    private boolean contains(UserStory us) { //Prüft ob die Liste aList den übergebenen Member beinhaltet und gibt einen Boolean zurück
        for(UserStory x : aList) {
            if(us.getId() == x.getId()){
                return true;
            }
        }
        return false;
    }

    public void deleteUserStory(int id) throws ContainerException {
        /*
        Welche Nachteile ergeben sich aus ihrer Sicht für ein solchen Fehlerhandling gegenüber einer Lösung mit Exceptions? Kurzes Statement!
            Exception ermöglichen es uns Fehler und Abstürze im Programmablauf sicher zu behandeln. Exceptions werden weiter gegeben bis Sie von uns behandelt oder ausgegeben werden.
            In diesem Fall wird keine Exception(Fehlermeldung) erzeugt, sondern lediglich ein String zurückgegeben. Das heißt auch das das Programm weiter läuft obwohl möglicherweise
            ein Kritischer Fehler aufgetaucht ist.
         */
        UserStory x = getUserStory(id);
        if(x == null) {
            throw new ContainerException();
        } else {
            aList.remove(x);
        }
    }

    private UserStory getUserStory(Integer id) { //Gibt den Member mit der übergebenen ID zurück oder wenn er nicht vorhanden ist null.
        for(UserStory x:aList) {
            if(id.equals(x.getId())){
                return x;
            }
        }
        return null;
    }

    public List<UserStory> getCurrentList() {
        return this.aList;
    }

    public int size() { //Anzahl der Member in aList
        return aList.size();
    }

    /*
    Uebung 3: Neue Methoden store() und load()
     */
    public void store() throws PersistenceException {
        if(pss == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy is set");
        }
        pss.save(aList);
    }

    public void load(boolean memoryType) throws PersistenceException {
        if (this.pss == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Strategy not initialized");
        }
        List<UserStory> liste = this.pss.load();
        if(memoryType) {
            try {
                for (UserStory us : liste) {
                    this.addUserStory(us);
                }
            } catch (ContainerException e) {
                e.printStackTrace();
            }
        } else {
            aList = liste;
        }
    }

    public void sortCointainer() {
        Collections.sort(aList); //Sortierung nach Prio
    }
}
