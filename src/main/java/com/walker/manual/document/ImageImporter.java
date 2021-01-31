package com.walker.manual.document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author walker624
 * @date 2021/1/31
 */
public class ImageImporter implements Importer{

    @Override
    public Document importFile(File file) throws IOException {
        final Map<String, String> attributes = new HashMap<>();
        attributes.put(Constants.PATH, file.getPath());

        final BufferedImage image = ImageIO.read(file);
        attributes.put(Constants.WIDTH, String.valueOf(image.getWidth()));
        attributes.put(Constants.HEIGHT, String.valueOf(image.getHeight()));
        attributes.put(Constants.TYPE, "IMAGE");

        return new Document(attributes);
    }
}
