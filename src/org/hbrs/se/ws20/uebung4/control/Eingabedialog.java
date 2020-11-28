package src.org.hbrs.se.ws20.uebung4.control;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Eingabedialog {

    public void beginnEingabe() {
        String eingabe = null;
        //System in ist die eingabe über die Tastatur
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                eingabe = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //split() teilt einen in einzelne Strings abhängig vom gewählten Argument hier: " ". Ausgabe ist in Form eines Arrays von Strings.
            String[] arrayEingaben = eingabe.split(" ");

            switch (arrayEingaben[0]) {
                case "help":
                    System.out.println("Folgende Befehle können verwendet werden: enter, store, load, dump, exit, help.");
                    break;
                case "enter":
                    break;
                case "store":
                    break;
                case "load":
                    break;
                case "dump":
                    break;
                case "exit":
                    break;
            }

        }


    }



}
