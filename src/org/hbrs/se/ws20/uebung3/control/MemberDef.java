package src.org.hbrs.se.ws20.uebung3.control;

import java.io.Serializable;

public class MemberDef implements Member, Serializable {

    private Integer id = null;

    public MemberDef(Integer id){
        this.id = id;
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString(){
        return "Member (ID = " + id + ")\n";
    }
}
