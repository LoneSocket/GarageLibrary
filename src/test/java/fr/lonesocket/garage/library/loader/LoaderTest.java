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
    public void getReferenceItems() throws Exception {
        Loader loader = new Loader();
        Map<Integer, String> referenceItems = loader.getReferenceItems();
        Assert.assertFalse(referenceItems.isEmpty());
    }
}
