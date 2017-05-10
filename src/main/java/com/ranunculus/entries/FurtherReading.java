package com.ranunculus.entries;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Ranunculus on 9/05/17.
 */
@ToString(callSuper=true)
public class FurtherReading extends MyNote {

    @Getter
    private static final String COLOR_CODE = "Blue";

    @Getter
    @Setter
    private String book;

}
