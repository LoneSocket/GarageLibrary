package fr.lonesocket.garage.library.model;

public class ReferenceItem {
    private final String id;
    private final String name;

    public ReferenceItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
