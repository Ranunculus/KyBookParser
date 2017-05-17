package com.ranunculus.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Ranunculus on 17/05/17.
 */
public class FileParser extends AbstractParser implements Parser<String> {

    @Override
    public Map<String, StringBuilder> parse(String data) {
        Path path;
        try {
            path = Paths.get(ClassLoader.getSystemResource(data).toURI());

            try (Stream<String> lines = Files.lines(path)) {
                parse(lines.collect(Collectors.toList()));
            } catch (IOException e) {
                e.printStackTrace();
                // TODO: 18/05/17 error handling
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            // TODO: 18/05/17 error handling
        }
        return result;
    }

}
