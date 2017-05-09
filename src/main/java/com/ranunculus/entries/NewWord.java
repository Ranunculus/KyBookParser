package com.ranunculus.entries;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Ranunculus on 9/05/17.
 */
@ToString
public class NewWord extends MyNote {

    public static final String COLOR_CODE = "Red";

    @Getter
    @Setter
    private String word;

//    todo: find a translation
//    private String transcription;
//    private String translation;
//    private String explanation;
}
