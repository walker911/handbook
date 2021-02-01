package com.walker.manual.document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/1
 */
public class TextFile {

    private final Map<String, String> attributes;

    private final List<String> lines;

    public TextFile(File file) throws IOException {
        this.attributes = new HashMap<>();
        this.lines = Files.readAllLines(Paths.get(file.getPath()));
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void addLineSuffix(final String prefix, final String attributeName) {
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                attributes.put(attributeName, line.substring(prefix.length()));
                break;
            }
        }
    }
}
