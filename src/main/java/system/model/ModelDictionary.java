package system.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "dictionariestable")
@Component
public class ModelDictionary implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "filePath")
    private String filePathStr;

    @Column(name = "pattern")
    private String patternOne;

    @ElementCollection
    private List<String> phrases = new ArrayList<>();

    @Transient
    private List<Phrase> dictionary = new ArrayList<Phrase>();

    public ModelDictionary() {
    }

    public ModelDictionary(String filePathStr, String patternOne, List<Phrase> dictionary) {
        this.filePathStr = filePathStr;
        this.patternOne = patternOne;
        this.dictionary = dictionary;
    }

    public ModelDictionary(ModelDictionary modelDictionary) {

    }


    public String getFilePathStr() {
        return filePathStr;
    }

    public void setFilePathStr(String filePathStr) {
        this.filePathStr = filePathStr;
    }

    public String getPatternOne() {
        return patternOne;
    }

    public void setPatternOne(String patternOne) {
        this.patternOne = patternOne;
    }

    public List<Phrase> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<Phrase> dictionary) {
        this.dictionary = dictionary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void phraseToString(){
        for (int i = 0; i < dictionary.size(); i++){
            this.phrases.add(dictionary.get(i).getIncomeWord() + " " + dictionary.get(i).getClearWord());
        }
    }


    @Override
    public String toString() {
        return "ModelDictionary{" +
                "filePathStr='" + filePathStr + '\'' +
                ", patternOne='" + patternOne + '\'' +
                ", dictionary=" + dictionary +
                '}';
    }
}
