package com.ranunculus.parser;

import com.ranunculus.LineType;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ranunculus on 17/05/17.
 */
public abstract class AbstractParser {
    final String DATA_SEPARATOR = "**************************************";
    private static Pattern COLOR_PATTERN = Pattern.compile("\\#\\d+.\\s\\[(\\w+){1}\\]");

    String currentColor;
    StringBuilder currentEntry;

    final static Map<String, StringBuilder> result = new HashMap<>(5);

    static {
        result.put("Purple", new StringBuilder());
        result.put("Red", new StringBuilder());
        result.put("Blue", new StringBuilder());
        result.put("Green", new StringBuilder());
        result.put("Yellow", new StringBuilder());
    }

    protected void parse(List<String> lines) {
        for (String currentLine : lines) {
            if (!StringUtils.isEmpty(currentLine)) {
                LineType currentLineType = determineCurrentLineType(currentLine);

                switch (currentLineType) {
                    case COLOR:
                        Matcher matcher = COLOR_PATTERN.matcher(currentLine);
                        if (matcher.find(0)) {
                            currentColor = matcher.group(1);
                            currentEntry = result.get(currentColor);

                            if (!StringUtils.isEmpty(currentEntry)) {
                                currentEntry.append("<br>");
                            }
                        }
                        break;
                    case EMPTY:
                    case SEPARATOR:
                        break;
                    default:
                        if (currentColor != null && currentEntry != null) {
                            currentEntry.append(processCurrentLine(currentLine, currentColor));
                        }
                }
            }
        }
    }

    protected LineType determineCurrentLineType(String currentLine) {
        Matcher matcher = COLOR_PATTERN.matcher(currentLine);
        if (matcher.matches()) {
            return LineType.COLOR;
        }
        if (DATA_SEPARATOR.equals(currentLine)) {
            return LineType.SEPARATOR;
        }
        if (currentLine.contains("Page")) {
            return LineType.PAGE;
        }
        return LineType.TEXT;
    }

    protected String processCurrentLine(String currentLine, String currentColor) {
        StringBuilder currentEntry = new StringBuilder();
        if (currentLine.trim().startsWith("-")) {
            currentEntry.append("<br><b>").append("My thoughts:").append("</b>").append("<i>").append(currentLine).append("</i>").append("<br>");
        } else if (currentLine.startsWith("Page")) {
            if (!"Red".equals(currentColor)) {
                currentEntry.append("<br>").append(currentLine).append("<br>");
            }
        } else if (!DATA_SEPARATOR.equals(currentLine) && !StringUtils.isEmpty(currentLine)) {
            if ("Red".equals(currentColor)) {
                currentEntry.append(currentLine.replaceAll("\"", ""));
            } else {
                currentEntry.append(currentLine).append(" ");
            }
        }
        return currentEntry.toString();
    }

}
