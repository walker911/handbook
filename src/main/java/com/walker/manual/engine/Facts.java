package com.walker.manual.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
public class Facts {

    private final Map<String, String> facts = new HashMap<>();

    public String getFact(final String name) {
        return facts.get(name);
    }

    public void addFact(final String name, final String value) {
        facts.put(name, value);
    }
}
