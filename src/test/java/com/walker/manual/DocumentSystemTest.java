package com.walker.manual;

import com.walker.manual.document.Attributes;
import com.walker.manual.document.Document;
import com.walker.manual.document.DocumentManagementSystem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/2
 */
public class DocumentSystemTest {

    private static final String RESOURCES = "src" + File.separator + "test" + File.separator + "=resources" + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String JOE_BLOGGS = "Joe Bloggs";

    DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws IOException {
        system.importFile(LETTER);

        Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, LETTER);
    }

    @Test
    public void shouldImportLetterAttributes() throws IOException {
        system.importFile(LETTER);

        Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.TYPE, JOE_BLOGGS);
    }

    @Test
    public void shouldImportImageAttributes() throws IOException {
        system.importFile(XRAY);

        Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.WIDTH, "320");
        assertAttributeEquals(document, Attributes.HEIGHT, "179");
    }

    private void assertAttributeEquals(final Document document, final String attributeName, final String expectedValue) {
        assertEquals(expectedValue, document.getAttribute(attributeName), "Document has the wrong value for " + attributeName);
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }
}
