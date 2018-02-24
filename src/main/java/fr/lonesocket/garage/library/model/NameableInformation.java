package fr.lonesocket.garage.library.model;

public abstract class NameableInformation {
    private final int id;
    private final String name;

    NameableInformation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
