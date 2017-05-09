package com.ranunculus.entries;

/**
 * Created by Ranunculus on 9/05/17.
 */
public class NotesFactory {

    public Note getNote(String noteColor) {
        if (noteColor == null) {
            return null;
        }
        if (noteColor.equals("Red")) return new NewWord();
        if (noteColor.equals("Green")) return new Idea();
        if (noteColor.equals("Yellow")) return new Quote();
        if (noteColor.equals("Blue")) return new FurtherReading();
        if (noteColor.equals("Purple")) return new NextAction();

        return null;
    }
}
