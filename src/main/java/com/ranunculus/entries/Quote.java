package com.ranunculus.entries;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * To Do:
 *
 * Created by Ranunculus on 9/05/17.
 */
@ToString
public class Quote extends MyNote {

    public static final String COLOR_CODE = "Yellow";

    @Getter
    @Setter
    private String quote;

}
