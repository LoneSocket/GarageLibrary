package fr.lonesocket.garage.library.model;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    private final String message;
    private final String steamLink;
    private final String garageLink;
    private List<Item> has;
    private List<Item> wants;

    public Offer(String steamLink, String garageLink, String message) {
        has = new ArrayList<>();
        wants = new ArrayList<>();
        this.steamLink = steamLink;
        this.garageLink = garageLink;
        this.message = message;
    }

    public String getSteamLink() {
        return steamLink;
    }

    public String getGarageLink() {
        return garageLink;
    }

    public String getMessage() {
        return message;
    }

    public List<Item> getHasItems() {
        return has;
    }

    public List<Item> getWantsItems() {
        return wants;
    }

    public void addHasItem(Item item) {
        has.add(item);
    }

    public void addWantsItem(Item item) {
        wants.add(item);
    }
}
