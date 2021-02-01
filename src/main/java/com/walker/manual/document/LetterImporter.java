package com.walker.manual.document;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author walker624
 * @date 2021/1/31
 */
public class LetterImporter implements Importer{
    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put("type", "letter");

        return new Document(attributes);
    }
}
