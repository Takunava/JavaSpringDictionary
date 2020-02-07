package system.dao;

import system.model.Phrase;

import java.util.List;

public interface DaoInterface {
    public List<Phrase> getPrases();
    public void setPrases();
}
