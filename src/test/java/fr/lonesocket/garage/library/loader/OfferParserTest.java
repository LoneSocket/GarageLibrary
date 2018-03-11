package fr.lonesocket.garage.library.loader;

import fr.lonesocket.garage.library.model.Certification;
import fr.lonesocket.garage.library.model.Offer;
import fr.lonesocket.garage.library.model.Paint;
import fr.lonesocket.garage.library.model.Platform;
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
    public void parseDocumentTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Assert.assertEquals(20, offers.size());
    }

    @Test
    public void parseItemTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Assert.assertEquals("496", offers.get(0).getHasItems().get(0).getId());
    }

    @Test
    public void getElapsedSecondsTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Offer offer = offers.get(5);
        Assert.assertEquals(1, offer.getElapsedSeconds());
    }

    @Test
    public void parsePlatformTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Offer offer = offers.get(0);
        Assert.assertEquals(Platform.STEAM, offer.getPlatform());
    }

    @Test
    public void getUsernameTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Offer offer = offers.get(3);
        Assert.assertEquals("Noobyto", offer.getUsername());
    }

    @Test
    public void getImageUrlTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Offer offer = offers.get(3);
        Assert.assertEquals("https://rocket-league.com/content/media/items/avatar/220px/fa44fde4291474239252.png", offer.getHasItems().get(0).getImgUrl());
    }

    @Test
    public void getGarageLinkTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Offer offer = offers.get(3);
        Assert.assertEquals("https://rocket-league.com/trade/bed8d643a33c", offer.getGarageLink());
    }

    @Test
    public void getPaintTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Offer offer = offers.get(3);
        Assert.assertEquals(Paint.ANY, offer.getHasItems().get(0).getPaint());
    }

    @Test
    public void getCertificationTest() throws Exception {
        List<Offer> offers = new OfferParser().parseOffers(doc, new ReferenceParser().getReferenceItems(doc));
        Offer offer = offers.get(3);
        Assert.assertEquals(Certification.ANY, offer.getHasItems().get(0).getCertification());
    }
}
