package com.ranunculus;

import com.ranunculus.parser.FileParser;
import com.ranunculus.parser.Parser;
import com.ranunculus.parser.TextAreaParser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by Ranunculus on 7/05/17.
 */
@Service
public class ParserFactory {

    public Parser getParser(String data) {
        if ("File".equals(data)) {

            return new FileParser();
        } else if ("Textarea".equals(data)) {

            return new TextAreaParser();
        }
        return null;
    }
}
