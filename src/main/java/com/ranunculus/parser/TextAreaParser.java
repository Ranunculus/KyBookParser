package com.ranunculus.parser;

import com.sun.tools.javac.util.List;

import java.util.Map;

/**
 * Created by Ranunculus on 17/05/17.
 */
public class TextAreaParser extends AbstractParser implements Parser<String>{


    @Override
    public Map<String, StringBuilder> parse(String data) {

        String[] lines = data.split("\\r\\n");
        parse(List.from(lines));

        return result;
    }

}
