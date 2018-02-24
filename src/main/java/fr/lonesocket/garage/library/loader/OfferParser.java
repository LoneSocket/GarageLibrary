package fr.lonesocket.garage.library.loader;

import fr.lonesocket.garage.library.model.Item;
import fr.lonesocket.garage.library.model.Offer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

class OfferParser {

    List<Offer> parseOffers(Document doc) {
        Element grid = doc.getElementsByClass("rlg-grid").get(1);
        Elements list = grid.child(0).child(6).children();
        List<Offer> offers = new ArrayList<>();
        for (Element element : list) {
            if (element.attr("class").contains("rlg-trade-display-container")) {
                offers.add(parseOffer(element));
            }
        }
        return offers;
    }

    private Offer parseOffer(Element element) {
        String note = null;
        String steamLink = null;
        String garageLink = null;
        Elements notes = element.getElementsByClass("rlg-trade-note-container");
        if (notes.size() == 1) {
            note = notes.get(0).child(1).child(0).child(1).text();
        }
        Elements links = element.getElementsByClass("rlg-trade-link-container");
        if (links.size() == 1) {
            Element p = links.get(0).child(0).child(0).child(0);
            garageLink = p.child(0).attr("href");
            if (p.children().size() >= 2) {
                steamLink = p.child(1).attr("href");
            }
        }
        Offer offer = new Offer(steamLink, garageLink, note);
        Element items = element.getElementsByClass("rlg-trade-display-items").get(0);
        Elements hasItems = items.child(0).children();
        for (Element item : hasItems) {
            offer.addHasItem(parseItem(item));
        }
        Elements wantItems = items.child(1).children();
        for (Element item : wantItems) {
            offer.addWantsItem(parseItem(item));
        }
        return offer;
    }

    private Item parseItem(Element element) {
        String[] information = element.attr("href").split("[=&]");
        int id = Integer.parseInt(information[1]);
        int certificationId = parsePossibleEmptyId(information[3]);
        int paintId = parsePossibleEmptyId(information[5]);
        Element displayItem = element.child(0);
        String imgUrl = displayItem.child(0).attr("src");
        Elements amounts = displayItem.getElementsByClass("rlg-trade-display-item__amount");
        int quantity = 1;
        if (!amounts.isEmpty()) {
            quantity = Integer.parseInt(amounts.get(0).text());
        }
        return new Item(id, certificationId, paintId, imgUrl, quantity);
    }

    private int parsePossibleEmptyId(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(str);
    }
}
