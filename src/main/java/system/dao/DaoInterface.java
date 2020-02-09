package system.dao;

import system.model.ModelDictionary;
import system.model.Phrase;

import java.util.Collection;
import java.util.List;

public interface DaoInterface {
    public Collection getPhrases();
    public void addPhrase(ModelDictionary dict);
    public void uploadPhrase(ModelDictionary dict);
}
