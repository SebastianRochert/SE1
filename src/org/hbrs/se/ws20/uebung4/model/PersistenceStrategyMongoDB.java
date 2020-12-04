package src.org.hbrs.se.ws20.uebung4.model;

import java.util.List;

public class PersistenceStrategyMongoDB<Member> implements PersistenceStrategy<Member> {
    @Override
    public void openConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void closeConnection() throws PersistenceException {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public void save(List<Member> member) {
        throw new UnsupportedOperationException("Not implemented!");

    }

    @Override
    public List<Member> load() {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
