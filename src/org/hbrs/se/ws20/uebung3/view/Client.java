package src.org.hbrs.se.ws20.uebung3.view;

import src.org.hbrs.se.ws20.uebung3.control.Container;
import src.org.hbrs.se.ws20.uebung3.control.ContainerException;
import src.org.hbrs.se.ws20.uebung3.control.Member;
import src.org.hbrs.se.ws20.uebung3.control.MemberDef;

public class Client {
    public static void main(String[] args) {
        Container con = Container.getInstance();

        Client.fuellen(con);
        Client.display(con);
    }

    public static void fuellen(Container con) {
        Member x = new MemberDef(11);
        Member y = new MemberDef(22);
        Member z = new MemberDef(33);

        try {
            con.addMember(x);
            con.addMember(y);
            con.addMember(z);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }

    public static void display(Container con) {
            MemberView.dump(con.getCurrentList());
    }
}
