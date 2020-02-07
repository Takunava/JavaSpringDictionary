package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.dao.DaoDictionary;
import system.model.ModelDictionary;
import system.model.Phrase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDictionary {

    @Autowired
    private ModelDictionary modelDictionary;



    public ModelDictionary returnDictionaries(){
        return modelDictionary;
    }

    public int addPhrase(Phrase phrase) throws IOException {
        boolean flag = false;
        if(validatePhrase(modelDictionary.getPatternOne(), phrase)) {
            for (int i = 0; i < modelDictionary.getDictionary().size(); i++) {
                if (modelDictionary.getDictionary().get(i).getIncomeWord().equals(phrase.getIncomeWord())) {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println("В словаре уже есть перевод данного слова");
                return 1;

            } else {
                modelDictionary.getDictionary().add(phrase);
                System.out.println("Успешно добавлено");
                this.uploadThisFileWhichDictionary(modelDictionary.getFilePathStr());
                return 0;
            }
            // this.UpdateFile();//апдейтим бд или файл
        }
        return 2;

    }
    public boolean deletePhrase(Phrase phrase) throws IOException {
        for (int i = 0; i < modelDictionary.getDictionary().size(); i++) {
            if(phrase.getIncomeWord().equals(modelDictionary.getDictionary().get(i).getIncomeWord()) && phrase.getClearWord().equals(modelDictionary.getDictionary().get(i).getClearWord())){
                modelDictionary.getDictionary().remove(i);
                this.uploadThisFileWhichDictionary(modelDictionary.getFilePathStr());
                return true;
            }
        }

        return false;
    }
    public Phrase searchPhrase(String word, int subjectOfSearch){
        switch (subjectOfSearch){
            case 1 : {
                for (int i = 0; i < modelDictionary.getDictionary().size(); i++) {
                    if(word.equals(
                            modelDictionary.
                                    getDictionary().
                                    get(i).
                                    getIncomeWord())){
                        return modelDictionary.getDictionary().get(i);
                    }
                }
                break;
            }
            case 2 : {
                for (int i = 0; i < modelDictionary.getDictionary().size(); i++) {
                    if(word.equals(
                            modelDictionary.
                                    getDictionary().
                                    get(i).
                                    getClearWord())){
                        return modelDictionary.getDictionary().get(i);
                    }
                }
                break;

            }
        }
        return null;
    }




    public void loadingDictionaryFromFile(String filePath, String pattern) throws IOException {

        FileInputStream fileReader = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileReader));
        List<Phrase> phrases = new ArrayList<Phrase>();
        while (bufferedReader.ready()) {
            String[] str = bufferedReader.readLine().split(" ");
            phrases.add(new Phrase(str[0], str[1]));
        }
        modelDictionary = new ModelDictionary(filePath,pattern, phrases);
    }

    public void uploadThisFileWhichDictionary(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
            if(filePath.equals(modelDictionary.getFilePathStr())){
                for(int j = 0; j < modelDictionary.getDictionary().size(); j++){
                    String key = modelDictionary.getDictionary().get(j).getIncomeWord();
                    String word = modelDictionary.getDictionary().get(j).getClearWord();
                    fileWriter.write(key + " " + word + "\n");
                }
            }
        fileWriter.close();
    }


    private boolean validatePhrase(String pattern, Phrase phrase){
        if(phrase.getIncomeWord().matches(pattern))
            return true;
        return false;
    }


}
