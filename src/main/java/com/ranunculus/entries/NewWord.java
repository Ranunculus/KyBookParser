package com.ranunculus.entries;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Ranunculus on 9/05/17.
 */
@ToString(callSuper=true)
public class NewWord extends MyNote {

    @Getter
    private static final String COLOR_CODE = "Red";

    @Getter
    @Setter
    private String word;

//    todo: find a translation
//    private String transcription;
//    private String translation;
//    private String explanation;
}
