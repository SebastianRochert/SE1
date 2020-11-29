package src.org.hbrs.se.ws20.uebung4.control;

import src.org.hbrs.se.ws20.uebung4.persistence.PersistenceException;
import src.org.hbrs.se.ws20.uebung4.persistence.PersistenceStrategy;
import src.org.hbrs.se.ws20.uebung4.persistence.PersistenceStrategyStream;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eingabedialog {

    public void beginnEingabe() {
        String input = null;
        //System in ist die eingabe über die Tastatur
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean status = true;

        Container con = Container.getInstance();
        con.setPss(new PersistenceStrategyStream<UserStory>());

        System.out.println("Wilkommen zum User Story Speichererungs Programm!");

        while (status) {
            try {
                System.out.print("Ihre Eingabe: ");
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //split() teilt einen in einzelne Strings abhängig vom gewählten Argument hier: " ". Ausgabe ist in Form eines Arrays von Strings.
            String[] arrayEingaben = input.split(" ");

            switch (arrayEingaben[0].toLowerCase()) {
                case "help":
                    System.out.println("Folgende Befehle können verwendet werden: enter, store, load, dump [merge, force], exit, help.");
                    break;
                case "enter":
                    ExtendedBufferedReader ebr = new ExtendedBufferedReader();

                    System.out.println("Bitte geben Sie die Informationen der UserStory an:");

                    int id = ebr.readLineInt("Bitte geben Sie die ID der User Story an: ");
                    String name = ebr.readLine("Bitte geben Sie den Namen der User Story an: ");
                    int aufwand = ebr.readLineInt("Bitte geben Sie den Aufwand der UserStory an (Gültige Werte: Zahlen der Fibonacci-Folge): ");
                    int mehrwert = ebr.readLineInt("Bitte geben Sie den Mehrwert der UserStory an (Gültige Werte: 1-5): ");
                    int strafe = ebr.readLineInt("Bitte geben Sie die Strafe der UserStory an (Gültige Werte: 1-5): ");
                    int risiko = ebr.readLineInt("Bitte geben Sie das Risiko der UserStory an (Gültige Werte: 1-5): ");

                    double prio = (((double)mehrwert + (double)strafe) / ((double)aufwand + (double)risiko));
                    prio = Math.round(100.0 * prio) / 100.0;
                    System.out.println("Die UserStory hat die Prio: " + prio);

                    UserStory us = new UserStory(id, name, aufwand, mehrwert, strafe, risiko, prio);
                    try {
                        con.addUserStory(us);
                    } catch (ContainerException e) {
                        System.out.println("Message: " + e.getMessage());
                    }
                    System.out.println("Die UserStory wurde erfolgreich hinzugefügt!");
                    break;
                case "store":
                    try {
                        con.store();
                    } catch (PersistenceException e) {
                        System.out.println("Message: " + e.getMessage());
                    }
                    break;
                case "load":
                    if (arrayEingaben.length == 1) {
                        System.out.println("Es wurde kein Parameter für den Befehl übergeben! Mögliche Parameter: [merge, force]");
                        break;
                    } else if(arrayEingaben[1].equals("merge")) {
                        try {
                            con.load(true);
                            System.out.println("MemoryType of load: merge");
                        } catch (PersistenceException e) {
                            System.out.println("Message: " + e.getMessage());
                        }
                        break;
                    } else if (arrayEingaben[1].equals("force")){
                        try {
                            con.load(false);
                            System.out.println("MemoryType of load: force");
                        } catch (PersistenceException e) {
                            System.out.println("Message: " + e.getMessage());
                        }
                        break;
                   }
                    System.out.println("Es wurde kein gültiger Parameter für den Befehl übergeben! Gültige Parameter: [merge, force]");
                    break;
                case "dump":
                    break;
                case "exit":
                    System.out.println("Das Programm wird beendet!");
                    status = false;
                    break;
            }
        }
    }
}