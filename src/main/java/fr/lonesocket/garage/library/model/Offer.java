package fr.lonesocket.garage.library.model;

import java.util.ArrayList;
import java.util.List;

public class Offer {
    private final String message;
    private final String steamLink;
    private final String garageLink;
    private final Platform platform;
    private final long postedTime;
    private List<Item> has;
    private List<Item> wants;

    public Offer(String steamLink, String garageLink, String message, Platform platform, long postedTime) {
        has = new ArrayList<>();
        wants = new ArrayList<>();
        this.steamLink = steamLink;
        this.garageLink = garageLink;
        this.message = message;
        this.platform = platform;
        this.postedTime = postedTime;
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

    public Platform getPlatform() {
        return platform;
    }

    public long getElapsedSeconds() {
        return (postedTime - System.currentTimeMillis()) / 1000;
    }
}
