package com.ranunculus.entries;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Ranunculus on 9/05/17.
 */
@ToString
public class NextAction extends MyNote {

    public static final String COLOR_CODE = "Purple";

    @Getter
    @Setter
    private String action;
}
