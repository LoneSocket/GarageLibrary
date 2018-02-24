package fr.lonesocket.garage.library.loader;

import fr.lonesocket.garage.library.model.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class OfferParserTest {
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
    public void parseDocumentTest() {
        List<Offer> offers = new OfferParser().parseOffers(doc);
        Assert.assertEquals(20, offers.size());
    }

    @Test
    public void parseItemTest() {
        List<Offer> offers = new OfferParser().parseOffers(doc);
        Assert.assertEquals(496, offers.get(0).getHasItems().get(0).getId());
    }
}
