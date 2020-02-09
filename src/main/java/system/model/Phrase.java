package system.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Phrase {


    private String incomeWord;

    private String clearWord;


    public Phrase() {
    }

    public Phrase(String incomeWord, String clearWord) {
        this.incomeWord = incomeWord;
        this.clearWord = clearWord;
    }

    public String getIncomeWord() {
        return incomeWord;
    }

    public void setIncomeWord(String incomeWord) {
        this.incomeWord = incomeWord;
    }

    public String getClearWord() {
        return clearWord;
    }

    public void setClearWord(String clearWord) {
        this.clearWord = clearWord;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "incomeWord='" + incomeWord + '\'' +
                ", clearWord='" + clearWord + '\'' +
                '}';
    }


}
