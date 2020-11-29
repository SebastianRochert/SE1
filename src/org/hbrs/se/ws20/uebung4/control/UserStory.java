package src.org.hbrs.se.ws20.uebung4.control;

import java.io.Serializable;

public class UserStory implements Comparable<UserStory>, Serializable {

    private int id = 0;
    private String name;
    private int aufwand = 0;
    private int mehrwert = 0;
    private int strafe = 0;
    private int risiko = 0;
    private double prio = 0.0;

    public UserStory(int id, String name, int aufwand, int mehrwert, int strafe, int risiko, double prio) {
        this.id = id;
        this.name = name;
        this.aufwand = aufwand;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.risiko = risiko;
        this.prio = prio;
    }

    public int getID() {
        return id;
    }

    @Override
    public int compareTo(UserStory us) {
        if(id == us.getID()) {
            return 1;
        }
        return 0;
    }
}
