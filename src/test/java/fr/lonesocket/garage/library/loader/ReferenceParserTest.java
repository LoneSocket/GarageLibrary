package fr.lonesocket.garage.library.loader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Map;

public class ReferenceParserTest {
    private static Document doc;

    @Before
    public void initialize() throws Exception {
        URL resourceFile = getClass().getClassLoader().getResource("trading.html");
        if (resourceFile == null) {
            throw new Exception("Cannot find the resource file trading.html");
        }
        File file = new File(resourceFile.getFile());
        doc = Jsoup.parse(file, "UTF-8");
    }

    @Test
    public void parseItemsTest() throws Exception {
        Map<String, String> referenceItems = new ReferenceParser().getReferenceItems(doc);
        Assert.assertEquals(455, referenceItems.size());
        Assert.assertEquals("Saptarishi", referenceItems.get("743"));
    }

    @Test(expected = ReferenceParserException.class)
    public void parseItemsWithoutItemsTest() throws Exception {
        Document doc = Jsoup.parse("<html></html>");
        new ReferenceParser().getReferenceItems(doc);
    }

    @Test
    public void parseCertificationsTest() throws Exception {
        Map<String, String> certifications = new ReferenceParser().getCertifications(doc);
        Assert.assertEquals(17, certifications.size());
        Assert.assertEquals("Paragon", certifications.get("7"));
    }

    @Test(expected = ReferenceParserException.class)
    public void parseCertificationsWithoutCertificationTest() throws Exception {
        Document doc = Jsoup.parse("<html></html>");
        new ReferenceParser().getCertifications(doc);
    }

    @Test
    public void parsePaintsTest() throws Exception {
        Map<String, String> paints = new ReferenceParser().getPaints(doc);
        Assert.assertEquals(15, paints.size());
        Assert.assertEquals("Titanium White", paints.get("3"));
    }

    @Test(expected = ReferenceParserException.class)
    public void parsePaintsWithoutPaintTest() throws Exception {
        Document doc = Jsoup.parse("<html></html>");
        new ReferenceParser().getPaints(doc);
    }
}
