package src.org.hbrs.se.ws20.uebung2.control;

public class ContainerException extends Exception{
    public Integer id;

    @Override
    public void printStackTrace() {
        System.out.println("Das Member-Objekt mit der ID" + id + "ist bereits vorhanden!");
    }

    public void setExceptionID(Integer id) {
        this.id = id;
    }
}
