package fr.lonesocket.garage.library.loader;

import fr.lonesocket.garage.library.model.Item;
import fr.lonesocket.garage.library.model.Offer;
import fr.lonesocket.garage.library.model.Platform;
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
        Elements platforms = element.getElementsByClass("rlg-trade-platform-name");
        String rlg = platforms.get(0).child(1).text();
        String username = rlg.substring(14, rlg.length());
        String platformName = platforms.get(0).text().toLowerCase();
        Platform platform;
        if (platformName.contains("steam")) {
            platform = Platform.STEAM;
        } else if (platformName.contains("psn")) {
            platform = Platform.PS4;
        } else if (platformName.contains("xbox")) {
            platform = Platform.XBOX;
        } else if (platformName.contains("switch")) {
            platform = Platform.SWITCH;
        } else {
            platform = Platform.NOT_KNOWN_PLATFORM;
        }
        Elements postedMessages = element.getElementsByClass("rlg-trade-display-added");
        String postedMessage = postedMessages.get(0).text();
        String[] postedMessageWords = postedMessage.split(" ");
        long elapsedTime = Long.parseLong(postedMessageWords[1]) * 1000; // seconds in milliseconds
        String unit = postedMessageWords[2].toLowerCase();
        if(unit.endsWith("s")){
            unit = unit.substring(0, unit.length()-1);
        }
        switch (unit){
            case "day":
                elapsedTime *= 24;
            case "hour":
                elapsedTime *= 60;
            case "minute":
                elapsedTime *= 60;
                break;
            default:
        }
        long postedTime = System.currentTimeMillis() + elapsedTime;
        Offer offer = new Offer(username, steamLink, garageLink, note, platform, postedTime);
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
