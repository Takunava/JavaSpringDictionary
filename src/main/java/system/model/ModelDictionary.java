package system.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDictionary {


    private Integer id;

    private String filePathStr;

    private String patternOne;

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

    @Override
    public String toString() {
        return "ModelDictionary{" +
                "filePathStr='" + filePathStr + '\'' +
                ", patternOne='" + patternOne + '\'' +
                ", dictionary=" + dictionary +
                '}';
    }
}
