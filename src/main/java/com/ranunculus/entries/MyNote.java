package com.ranunculus.entries;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Personal notes on the matter
 * <p>
 * Created by Ranunculus on 9/05/17.
 */
@ToString
public class MyNote implements Note {

    @Getter
    @Setter
    private String myNote;

    @Getter
    @Setter
    private String page;

    @Getter
    @Setter
    private String color;

    @Getter
    @Setter
    private String entry;


}
