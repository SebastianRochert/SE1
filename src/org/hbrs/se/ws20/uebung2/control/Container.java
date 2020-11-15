package src.org.hbrs.se.ws20.uebung2.control;

import java.util.ArrayList;
import java.util.List;

public class Container {

    public List<Member> aList = new ArrayList<>();

    public void addMember(Member member) throws ContainerException {
        /*
          ist das übergebene Member Objekt bereits in dem Container vorhanden? (Überprüfen)
          wenn ja, dann throw Exception vom Typ ContainerException
         */
        if (contains(member)) {
            ContainerException exception = new ContainerException();
            exception.setExceptionID(member.getID());
            throw exception;
        }
        aList.add(member);
    }

    public boolean contains(Member member) {
        Integer id = member.getID();
        for(Member x : aList) {
            if(member.getID().equals(x.getID())){
                return true;
            }
        }
        return false;
    }

    public String deleteMember(Integer id) {
        Member x = getMember(id);
        if(x == null) {
            return "Member ist nicht in der Liste enthalten!";
        } else {
            aList.remove(x);
            return "Der Member mit der ID: " + id + " wurde erfolgreich gelöscht!";
        }
    }

    private Member getMember(Integer id) {
        for(Member x:aList) {
            if(id.equals(x.getID())){
                return x;
            }
        }
        return null;
    }

    public void dump() {
        System.out.println("Alle IDs der aktuell abgespeicherten Objekte im Container:");
        for(Member x:aList){
            System.out.println(x.toString());
        }
    }

    public int size() {
        return aList.size();
    }
}
