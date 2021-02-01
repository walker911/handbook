package com.walker.manual.document;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 发票
 * </p>
 *
 * @author mu qin
 * @date 2021/2/1
 */
public class InvoiceImporter implements Importer{

    private static final String NAME_PREFIX = "Dear ";
    private static final String PATIENT = "name";
    private static final String AMOUNT_PREFIX = "Amount:";
    private static final String AMOUNT = "amount";

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put("type", "invoice");

        return new Document(attributes);
    }
}
