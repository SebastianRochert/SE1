package src.org.hbrs.se.ws20.uebung4.view;

import src.org.hbrs.se.ws20.uebung4.model.Container;

public class Ausgabedialog {
    public void dump(Container con) {
        System.out.println();
        con.getCurrentList().forEach(userStory -> System.out.println(userStory.toString()));
    }

    public void dumpAufwand(Container con, int a) {
        con.getCurrentList().stream()
                .filter(userStory -> userStory.getAufwand() > a)      //Filter
                .forEach(userStory -> System.out.println(userStory.toString())); //Reduce (forEach)
    }

    //Full Filter Map Reduce Pattern:
//                    con.getCurrentList().stream()
//                            .filter(item -> item.getAufwand() > 3)      //Filter
//                            .map(item -> item.getName())                //Map
//                            .forEach(item -> System.out.println(item)); //Reduce (forEach)
}
