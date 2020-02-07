package system.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import system.model.ModelDictionary;
import system.model.Phrase;

import java.io.IOException;

public interface InterfaceController {
    public ModelAndView selectDictionary();
    public ModelDictionary viewDictionary
            (@RequestParam ("Path")String path) throws IOException;
    public ModelDictionary addDictionary
            (@RequestParam ("incomeWord")String incomeWord,
             @RequestParam("clearWord") String clearWord) throws IOException;
    public Phrase searchPhrase
            (@RequestParam ("Word")String word,
             @RequestParam("subjectOfSearch") int subjectOfSearch) throws IOException;
    public ModelDictionary deletePhrase
            (@RequestParam ("incomeWord")String incomeWord,
             @RequestParam("clearWord") String clearWord) throws IOException;

}
