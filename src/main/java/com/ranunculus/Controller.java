package com.ranunculus;

import com.ranunculus.parser.FileParser;
import com.ranunculus.parser.TextAreaParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ranunculus on 7/05/17.
 */

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    public ParserFactory parserFactory;

    @RequestMapping("/")
    public String index(){
        return "parser";
    }

    @RequestMapping("/file")
    public String parseFile(Model model) {
        FileParser parser = (FileParser) parserFactory.getParser("File");
        if (parser == null) {
            return "error";
            // TODO: 18/05/17 error handling
        }
        // TODO: 18/05/17 do smth about generics
        model.addAllAttributes(parser.parse("notes_example.txt"));
        return "ok";
    }

    @RequestMapping(value = "/textarea", method = RequestMethod.POST)
    public String parseTextArea(Model model, @RequestParam String notes) {
        TextAreaParser parser = (TextAreaParser) parserFactory.getParser("Textarea");
        if (parser == null) {
            return "error";
            // TODO: 18/05/17 error handling
        }
        model.addAllAttributes(parser.parse(notes));
        return "ok";
    }


}
