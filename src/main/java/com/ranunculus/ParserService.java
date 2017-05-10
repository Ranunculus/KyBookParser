package com.ranunculus;

import com.ranunculus.entries.Note;
import com.ranunculus.entries.NotesFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
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

    public void parseFromFile(String fileName) {
        Path path = null;
        try {
            path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());

            try (Stream<String> lines = Files.lines(path)) {
                Iterator<String> iterator = lines.iterator();
                Note newNote = null;
                NotesFactory notes = new NotesFactory();

                while (iterator.hasNext()) {
                    String currentLine = iterator.next();
                    if (StringUtils.isEmpty(currentLine)) {
                        continue;
                    }
                    //is it next entry?
                    Pattern pattern = Pattern.compile("\\#[1-9]+.\\s\\[(\\w+){1}\\]");
                    Matcher matcher = pattern.matcher(currentLine);
                    if (matcher.find()) {
                        if (newNote != null) {
                            System.out.println(newNote);
                            // TODO: 9/05/17 write to a file
                        }
                        newNote = notes.getNote(matcher.group(1));
                    } else if (newNote != null) {
                        if (currentLine.trim().startsWith("-")){
                            newNote.setMyNote(currentLine);
                        } else if (currentLine.startsWith("Page")) {
                            newNote.setPage(currentLine.substring(5));
                        } else if (!StringUtils.isEmpty(currentLine)) {
                            // TODO: 11/05/17 set EntryBody
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
