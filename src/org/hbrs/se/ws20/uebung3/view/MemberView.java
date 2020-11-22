package src.org.hbrs.se.ws20.uebung3.view;

import src.org.hbrs.se.ws20.uebung3.control.Member;

import java.util.List;

public class MemberView {
    public void dump(List<Member> liste) { //Ausgabe aller IDs der vorhandenen Member in der Console
        System.out.print("Alle IDs der aktuell abgespeicherten Objekte im Container:\n");
        for(Member x:liste){
            System.out.print(x.toString());
        }
    }
}
