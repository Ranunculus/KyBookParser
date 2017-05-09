package com.ranunculus.entries;

import lombok.Getter;
import lombok.Setter;

/**
 * Personal notes on the matter
 * <p>
 * Created by Ranunculus on 9/05/17.
 */
public abstract class MyNote implements Note {

    @Getter
    @Setter
    private String myNote;

    /**
     * {@inheritDoc}
     */
    @Getter
    @Setter
    private String page;
}
