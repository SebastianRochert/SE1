package src.org.hbrs.se.ws20.uebung4.view;

import src.org.hbrs.se.ws20.uebung4.model.Container;
import src.org.hbrs.se.ws20.uebung4.controller.Eingabedialog;
import src.org.hbrs.se.ws20.uebung4.model.UserStory;
import src.org.hbrs.se.ws20.uebung4.model.persistence.PersistenceStrategy;
import src.org.hbrs.se.ws20.uebung4.model.persistence.PersistenceStrategyStream;

public class Main {
    public static void main (String[] args) {
        Eingabedialog eingabedialog = new Eingabedialog();
        Container con = Container.getInstance();

        PersistenceStrategy<UserStory> usPSS = new PersistenceStrategyStream<UserStory>();
        con.setPss(usPSS);

        eingabedialog.beginnEingabe(con);

        //To-DO
        //Main muss pss setzten und COntainer erstellen und den Stream in den Cointainer setzten
    }
}
