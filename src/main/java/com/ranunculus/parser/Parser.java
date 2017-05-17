package com.ranunculus.parser;

import java.util.Map;

/**
 * Created by Ranunculus on 17/05/17.
 */
public interface Parser<T> {

    public Map<String, StringBuilder> parse(T data);

}
