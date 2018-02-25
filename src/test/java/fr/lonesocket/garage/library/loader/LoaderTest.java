package fr.lonesocket.garage.library.loader;

import fr.lonesocket.garage.library.model.Offer;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class LoaderTest {
    @Test
    public void getOffersTest() throws Exception {
        Loader loader = new Loader();
        List<Offer> offers = loader.getOffers();
        Assert.assertFalse(offers.isEmpty());
    }

    @Test
    public void getReferenceItemsTest() throws Exception {
        Loader loader = new Loader();
        Map<String, String> referenceItems = loader.getReferenceItems();
        Assert.assertFalse(referenceItems.isEmpty());
    }

    @Test
    public void getCertificationsTest() throws Exception {
        Loader loader = new Loader();
        Map<String, String> certifications = loader.getCertifications();
        Assert.assertFalse(certifications.isEmpty());
    }

    @Test
    public void getPaints() throws Exception {
        Loader loader = new Loader();
        Map<String, String> paints = loader.getPaints();
        Assert.assertFalse(paints.isEmpty());
    }

    @Test
    public void getPlatforms() throws Exception {
        Loader loader = new Loader();
        Map<String, String> platforms = loader.getPlatforms();
        Assert.assertFalse(platforms.isEmpty());
    }
}
