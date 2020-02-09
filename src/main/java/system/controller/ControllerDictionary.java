package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.ModelDictionary;
import system.model.Phrase;
import system.service.ServiceDictionary;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Controller
@RequestMapping("/dictionary")
public class ControllerDictionary implements InterfaceController {

    final static Logger logger = Logger.getLogger(String.valueOf(ControllerDictionary.class));


    @Autowired
    private ServiceDictionary serviceDictionary;

    @Override
    @RequestMapping(value = "/select")
    public ModelAndView selectDictionary() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("select_dictionary");
        return modelAndView;
    }


    @Override
    @RequestMapping("/view" )
    @ResponseBody
    public ModelDictionary viewDictionary
            (@RequestParam ("Path")String path) throws IOException {

        logger.info(path);

        if(path.equals("C:\\\\temp\\\\dictionary.txt")){
            serviceDictionary.loadingDictionaryFromFile(path, "^[a-zA-Z0-9]+$");
        } else if(path.equals("C:\\\\temp\\\\dictionary1.txt")) {
            serviceDictionary.loadingDictionaryFromFile(path, "[1-9]+");
        }
        logger.info(serviceDictionary.toString());
        return serviceDictionary.returnDictionaries();
    }


    @Override
    @RequestMapping("/add")
    @ResponseBody
    public ModelDictionary addDictionary
            (@RequestParam ("incomeWord")String incomeWord,
             @RequestParam("clearWord") String clearWord) throws IOException {
        logger.info(incomeWord);
        logger.info(clearWord);
        int addingStatus = serviceDictionary.addPhrase(new Phrase(incomeWord, clearWord));
        if(addingStatus == 1){
           return null;
        }
        logger.info(serviceDictionary.toString());
        return serviceDictionary.returnDictionaries();
    }


    @Override
    @RequestMapping("/search")
    @ResponseBody
    public Phrase searchPhrase
            (@RequestParam ("Word")String word,
             @RequestParam("subjectOfSearch") int subjectOfSearch) throws IOException {
        logger.info(word);
        logger.info(Integer.toString(subjectOfSearch));
        logger.info((Supplier<String>) serviceDictionary.searchPhrase(word, subjectOfSearch));
        Phrase tmp = serviceDictionary.searchPhrase(word, subjectOfSearch);
        return  tmp;
    }



    @RequestMapping("/delete")
    @ResponseBody
    @Override
    public ModelDictionary deletePhrase
            (@RequestParam ("incomeWord")String incomeWord,
             @RequestParam("clearWord") String clearWord) throws IOException {
        logger.info(incomeWord);
        logger.info(clearWord);
        Phrase phrase = new Phrase(incomeWord, clearWord);
        serviceDictionary.deletePhrase(phrase);
        logger.info(serviceDictionary.toString());
        return serviceDictionary.returnDictionaries();
    }


}
