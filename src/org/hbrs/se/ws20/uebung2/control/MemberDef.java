package src.org.hbrs.se.ws20.uebung2.control;

public class MemberDef implements Member{

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
        return "Member (ID = " + id + ")";
    }
}
