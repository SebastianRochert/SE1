package src.org.hbrs.se.ws20.uebung4.controller;

import src.org.hbrs.se.ws20.uebung4.model.Container;
import src.org.hbrs.se.ws20.uebung4.model.ContainerException;
import src.org.hbrs.se.ws20.uebung4.model.ExtendedBufferedReader;
import src.org.hbrs.se.ws20.uebung4.model.UserStory;
import src.org.hbrs.se.ws20.uebung4.model.persistence.PersistenceException;
import src.org.hbrs.se.ws20.uebung4.view.Ausgabedialog;

import java.io.*;

public class Eingabedialog {

    public void beginnEingabe(Container con) {
        String input = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //System in ist die eingabe über die Tastatur

        boolean status = true;

        ExtendedBufferedReader ebr = new ExtendedBufferedReader();

        System.out.println("Wilkommen zum User Story Speichererungs Programm!");

        while (status) {
            System.out.print("Ihre Eingabe: ");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //split() teilt einen in einzelne Strings abhängig vom gewählten Argument hier: " ". Ausgabe ist in Form eines Arrays von Strings.
            String[] arrayEingaben = input.split(" ");

            switch (arrayEingaben[0].toLowerCase()) {
                case "help":
                    System.out.println("Folgende Befehle können verwendet werden:\n" +
                            "enter \t(zum hinzufügen einer User Story zum RAM) \n" +
                            "store \t(speichern aller User Stories aus dem RAM auf externen HDD\n" +
                            "load [merge, force] (laden aller extern gespeichertern User Stories in den RAM. [Force überschreiben des RAM], [merge hinzufügen zum RAM])\n" +
                            "dump \t(Konsolenausgabe aller User Stories aus dem RAM)\n" +
                            "delete \t(Löscht eine User Story mit einer bestimmten ID aus dem RAM\n" +
                            "exit \t(Beendet das Programm)\n" +
                            "help \t(Zeigt alle möglichen Befehle an)\n");
                    break;
                case "enter":
                    System.out.println("Bitte geben Sie die Informationen der UserStory an:");

                    int id = ebr.readLineInt("Bitte geben Sie die ID der User Story an: ");
                    String name = ebr.readLine("Bitte geben Sie den Namen der User Story an: ");
                    int aufwand = ebr.readLineInt("Bitte geben Sie den Aufwand der UserStory an (Gültige Werte: Zahlen der Fibonacci-Folge): ");

                    if(!isFib(aufwand)) {
                        System.out.println("Gültige Werte sind nur Zahlen der Fibonacci-Folge!");
                        break;
                    }

                    int mehrwert = ebr.readLineInt("Bitte geben Sie den Mehrwert der UserStory an (Gültige Werte: 1-5): ");
                    int strafe = ebr.readLineInt("Bitte geben Sie die Strafe der UserStory an (Gültige Werte: 1-5): ");
                    int risiko = ebr.readLineInt("Bitte geben Sie das Risiko der UserStory an (Gültige Werte: 1-5): ");

                    if(mehrwert > 5 || strafe > 5 || risiko > 5 || mehrwert < 1 || strafe < 1 || risiko < 1) {
                        System.out.println("Gültige Werte sind nur Zahlen zwischen 1 und 5!");
                        break;
                    }

                    double prio = (((double)mehrwert + (double)strafe) / ((double)aufwand + (double)risiko));
                    prio = Math.round(100.0 * prio) / 100.0;
                    System.out.println("Die UserStory hat die Prio: " + prio);

                    UserStory us = new UserStory(id, name, aufwand, mehrwert, strafe, risiko, prio);
                    try {
                        con.addUserStory(us);
                    } catch (ContainerException e) {
                        e.printStackTrace();
                        break;
                    }
                    System.out.println("Die UserStory wurde erfolgreich hinzugefügt!");
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
                            e.printStackTrace();
                        }
                        break;
                    } else if (arrayEingaben[1].equals("force")){
                        try {
                            con.load(false);
                            System.out.println("MemoryType of load: force");
                        } catch (PersistenceException e) {
                            System.out.println("Message: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;
                    }
                    System.out.println("Es wurde kein gültiger Parameter für den Befehl übergeben! Gültige Parameter: [merge, force]");
                    break;
                case "store":
                    try {
                        con.store();
                    } catch (PersistenceException e) {
                        e.printStackTrace();
                        System.out.println("Message: " + e.getMessage());
                    }
                    break;
                case "dump":
                    Ausgabedialog ad = new Ausgabedialog();

                    if(con.getCurrentList().isEmpty()) {
                        System.out.println("Es sind keine UserStories vorhanden die ausgegeben werden könnten!");
                        break;
                    }
                    con.sortCointainer();

                    if(arrayEingaben.length == 1) {
                        ad.dump(con);
                        break;
                    } else if(arrayEingaben[1].equals("aufwand")) {
                        int a = Integer.parseInt(arrayEingaben[2]);
                        ad.dumpAufwand(con, a);
                        break;
                    }
                    break;
                case "delete":
                    int idToDel = ebr.readLineInt("Bitte geben Sie die ID der User Story an die gelöscht werden soll: ");
                    try {
                        con.deleteUserStory(idToDel);
                    } catch (ContainerException e) {
                        System.out.println("Die User Story mit der id " + idToDel + " ist nicht im Container enthalten!");
                        break;
                    }
                    System.out.println("Die User Story mit der ID: " + idToDel + " wurde erfolgreich gelöscht!");
                    break;
                case "exit":
                    System.out.println("Das Programm wird beendet!");
                    status = false;
                    break;
                default:
                    System.out.println("Es wurde kein Gültiger Befehl eingegeben! Alle Gültigen Befehle sehen Sie wenn Sie den Befehl 'help' eingeben.");
                    break;
            }
        }
    }

    public boolean isFib(int aufwand) {
//        Fibonacci Mathematisch
//        int[] fib = {1,2,3,5,8,13,21,34,55,89,144};
        int[] fib = {1,2,3,5,8,13,20,40,100};
        for(int x : fib) {
            if(aufwand == x) {
                return true;
            }
        }
        return false;
    }

//    IsFib Alternative
//    public boolean isFib(int i) {
//        int firstTerm = 0;
//        int secondTerm = 1;
//        int thirdTerm = 0;
//        while (thirdTerm < i) {
//            thirdTerm = firstTerm + secondTerm;
//            firstTerm = secondTerm;
//            secondTerm = thirdTerm;
//        }
//        if (thirdTerm == i) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}