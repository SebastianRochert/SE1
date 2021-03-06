package src.org.hbrs.se.ws20.uebung4.model.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<UserStory> implements PersistenceStrategy<UserStory> {

    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    String LOCATION = "userStoryStore.txt";

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    @Override
    public void openConnection() throws PersistenceException {
        try {
            fos = new FileOutputStream(LOCATION);
            // Initiating the Stream (can also be moved to method openConnection()... ;-)
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Error in opening the connection, File could not be found!");
        }
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Error in opening the connection, problems with the stream!");
        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            // Closing the outputstreams for storing
            if (oos != null) oos.close();
            if (fos != null) fos.close();

            // Closing the inputstreams for loading
            if (ois != null) ois.close();
            if (fis != null) fis.close();
        } catch( IOException e ) {
            // Lazy solution: catching the exception of any closing activity ;-)
            throw new PersistenceException(PersistenceException.ExceptionType.ClosingFailure , "error while closing connections");
        }
    }

    @Override
    /*
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<UserStory> us) throws PersistenceException {
        openConnection();
        try {
            oos.writeObject(us);
            if(us.size() != 0) {
                System.out.println("Es wurden die UserStories erfolgreich gespeichert. Anzahl: " + us.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.SaveFailure, "Fehler beim speichern der Daten!");
        } finally {
            closeConnection();
        }
    }

    @Override
    /*
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<UserStory> load() throws PersistenceException {
        // Some Coding hints ;-)
        //ObjectInputStream ois = null;
        //FileInputStream fis = null;

        List<UserStory> newListe =  null;

        // Reading and extracting the list (try .. catch committed here)
        try {
            fis = new FileInputStream(LOCATION);
            ois = new ObjectInputStream(fis);

            //Auslesen der Liste
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                newListe = (List) obj;
            }
            if(newListe == null) {
                throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Der Storage ist leer, es konnten keine Objekte geladen werden!");
            }
            System.out.println("Die UserStories wurden erfolgreich geladen. Anzahl: " + newListe.size());
            return newListe;
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Fehler beim laden der Datei!");
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Fehler beim laden der Datei! Class not found!");
        }
        finally { // and finally close the streams (guess where this could be...?)
            closeConnection();
        }
    }
}