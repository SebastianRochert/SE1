package src.org.hbrs.se.ws20.uebung3.persistence;

import src.org.hbrs.se.ws20.uebung2.control.ContainerException;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {

//    private static FileOutputStream fos;
//    private static ObjectOutputStream oos;
//    private static FileInputStream fis;
//    private static ObjectInputStream ois;
//    private static String file;
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    String file = "file.txt";

    @Override
    public void openConnection() throws PersistenceException {
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            // Initiating the Stream (can also be moved to method openConnection()... ;-)
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            fos.close();
            oos.close();
            fis.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        openConnection();
        try {
            for(Member x : member) {
                oos.writeObject(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException  {
        // Some Coding hints ;-)
        //ObjectInputStream ois = null;
        //FileInputStream fis = null;
        openConnection();

        List<Member> newListe =  null;

        // Reading and extracting the list (try .. catch committed here)
        try {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                newListe = (List) obj;
            }
            return newListe;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally { // and finally close the streams (guess where this could be...?)
            closeConnection();
        }
        return null;
    }
}