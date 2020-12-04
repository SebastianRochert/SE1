package src.org.hbrs.se.ws20.uebung4.model;

import java.io.Serializable;

public class UserStory implements Comparable<UserStory>, Serializable {

    private int id;
    private String name;
    private int aufwand;
    private int mehrwert;
    private int strafe;
    private int risiko;
    private double prio;

    public UserStory(int id, String name, int aufwand, int mehrwert, int strafe, int risiko, double prio) {
        this.id = id;
        this.name = name;
        this.aufwand = aufwand;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.risiko = risiko;
        this.prio = prio;
    }

    public double getPrio() {
        return prio;
    }

    public int getId() {
        return id;
    }

    public int getAufwand() {
        return aufwand;
    }
    public int getRisiko() {
        return risiko;
    }

    public int getStrafe() {
        return strafe;
    }

    public String getName() {
        return name;
    }

    public int getMehrwert() {
        return mehrwert;
    }

    @Override
    public int compareTo(UserStory us) {
        if(us.getPrio() == this.getPrio()) {
            return 0;
        } else if(us.getPrio() > this.getPrio()) {
            return 1;
        }
        return -1;
    }

    public String toString() {
        return "ID: \t\t" + id +
                "\nName: \t\t" + name +
                "\nAufwand: \t" + aufwand +
                "\nMehrwert: \t" + mehrwert +
                "\nStrafe: \t" + strafe +
                "\nRisiko: \t" + risiko +
                "\nPrio: \t\t" + prio + "\n";
    }
}
