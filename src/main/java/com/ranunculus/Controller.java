package com.ranunculus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ranunculus on 7/05/17.
 */

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    public ParserService parserService;

    @RequestMapping("/")
    public String index(){
        return "parser";
    }

    @RequestMapping("/file")
    public String parseFile(Model model) {
        model.addAllAttributes(parserService.parseFromFile("notes_example.txt"));
        return "ok";
    }

}
