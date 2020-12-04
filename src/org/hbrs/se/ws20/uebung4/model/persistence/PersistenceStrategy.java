package src.org.hbrs.se.ws20.uebung4.model.persistence;

import java.util.List;

/**
 * Interface for defining basic methods for a persistence mechanism
 * Each concrete algorithm (i.e. strategy) must implement this method
 * This interface corresponds to the abstract strategy w.r.t. to the
 * Strategy Design Pattern (GoF).
 *
 * @param <E>
 */
public interface PersistenceStrategy<E> {
    public void openConnection() throws PersistenceException;
    public void closeConnection() throws PersistenceException;
    public void save(List<E> member) throws PersistenceException;
    public List<E> load() throws PersistenceException;
}
