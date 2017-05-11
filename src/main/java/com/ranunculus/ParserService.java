package com.ranunculus;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Ranunculus on 7/05/17.
 */
@Service
public class ParserService {

    final String DATA_SEPARATOR = "**************************************";
    final String BREAK = "";

    public Map<String, StringBuilder> parseFromFile(String fileName) {
        Path path = null;
        Map<String, StringBuilder> result = new HashMap<>(5);
        result.put("Purple", new StringBuilder());
        result.put("Red", new StringBuilder());
        result.put("Blue", new StringBuilder());
        result.put("Green", new StringBuilder());
        result.put("Yellow", new StringBuilder());

        try {
            path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());

            try (Stream<String> lines = Files.lines(path)) {
                Iterator<String> iterator = lines.iterator();
                String currentColor = null;
                StringBuilder currentEntry = null;
                while (iterator.hasNext()) {
                    String currentLine = iterator.next();
                    if (StringUtils.isEmpty(currentLine)) {
                        continue;
                    }
                    //is it next entry?
                    Pattern pattern = Pattern.compile("\\#\\d+.\\s\\[(\\w+){1}\\]");
                    Matcher matcher = pattern.matcher(currentLine);

                    if (matcher.find()) {
                        currentColor = matcher.group(1);
                        currentEntry = result.get(currentColor);
                        if (!StringUtils.isEmpty(currentEntry)) {
                            currentEntry.append("<br>");
                        }
                    } else if (currentColor != null && currentEntry != null) {
                        if (currentLine.trim().startsWith("-"))  {
                            currentEntry.append("<br>").append("My thoughts:").append("<br>").append("<i>").append(currentLine).append("</i>").append("<br>");
                        } else if (currentLine.startsWith("Page")) {
                            if (!"Red".equals(currentColor)) {
                                currentEntry.append(currentLine).append("<br>");
                            }
                        } else if (!DATA_SEPARATOR.equals(currentLine) && !StringUtils.isEmpty(currentLine)) {
                            if ("Red".equals(currentColor)) {
                                currentEntry.append(currentLine.replaceAll("\"", ""));
                            }
                            currentEntry.append(currentLine);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

}
