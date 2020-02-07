package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.ModelDictionary;
import system.model.Phrase;
import system.service.ServiceDictionary;

import java.io.IOException;
import java.util.logging.Logger;

@Controller
@RequestMapping("/dictionary")
public class ControllerDictionary implements InterfaceController{

    private static final Logger log = Logger.getLogger(String.valueOf(ControllerDictionary.class));


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

        log.info(path);
        serviceDictionary.loadingDictionaryFromFile(path, "^[a-zA-Z0-9]+$");
        log.info(serviceDictionary.toString());
        return serviceDictionary.returnDictionaries();
    }


    @Override
    @RequestMapping("/add")
    @ResponseBody
    public ModelDictionary addDictionary
            (@RequestParam ("incomeWord")String incomeWord,
             @RequestParam("clearWord") String clearWord) throws IOException {
        log.info(incomeWord);
        log.info(clearWord);
        int addingStatus = serviceDictionary.addPhrase(new Phrase(incomeWord, clearWord));
        if(addingStatus == 1){
           return null;
        }
        log.info(serviceDictionary.toString());
        return serviceDictionary.returnDictionaries();
    }


    @Override
    @RequestMapping("/search")
    @ResponseBody
    public Phrase searchPhrase
            (@RequestParam ("Word")String word,
             @RequestParam("subjectOfSearch") int subjectOfSearch) throws IOException {
        log.info(word);
        //log.info(subjectOfSearch);
        //log.info(serviceDictionary.searchPhrase(phrase, Integer.getInteger(subjectOfSearch)));
        Phrase tmp = serviceDictionary.searchPhrase(word, subjectOfSearch);
        return  tmp;
    }


    @Override
    @RequestMapping("/delete")
    @ResponseBody
    public ModelDictionary deletePhrase
            (@RequestParam ("incomeWord")String incomeWord,
             @RequestParam("clearWord") String clearWord) throws IOException {
        log.info(incomeWord);
        log.info(clearWord);
        Phrase phrase = new Phrase(incomeWord, clearWord);
        serviceDictionary.deletePhrase(phrase);
        log.info(serviceDictionary.toString());
        return serviceDictionary.returnDictionaries();
    }






}
