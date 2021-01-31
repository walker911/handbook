package com.walker.manual.document;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author walker624
 * @date 2021/1/31
 */
public class Document {

    private final Map<String, String> attributes;

    Document(final Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }
}
