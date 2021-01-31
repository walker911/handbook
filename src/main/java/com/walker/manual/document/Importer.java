package com.walker.manual.document;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author walker624
 * @date 2021/1/31
 */
public interface Importer {

    Document importFile(File file) throws IOException;

}
