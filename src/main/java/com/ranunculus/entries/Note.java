package com.ranunculus.entries;

/**
 * Created by Ranunculus on 9/05/17.
 */
public interface Note {

    String getPage();
    void setPage(String currentLine);

    String getMyNote();
    void setMyNote(String currentLine);

    String getColor();
    void setColor(String currentLine);

    String getEntry();
    void setEntry(String currentLine);

}
