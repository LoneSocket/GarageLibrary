package fr.lonesocket.garage.library.model;

public enum Paint {
    NOT_KNOWN("NK", "Not Known"),
    ANY("0", "Any"),
    NONE("N", "None"),
    BURNT_SIENNA("1", "Burnt Sienna"),
    LIME("2", "Lime"),
    TITANIUM_WHITE("3", "Titanium White"),
    COBALT("4", "Cobalt"),
    CRIMSON("5", "Crimson"),
    FOREST_GREEN("6", "Forest Green"),
    GREY("7", "Grey"),
    ORANGE("8", "Orange"),
    PINK("9", "Pink"),
    PURPLE("10", "Purple"),
    SAFFRON("11", "Saffron"),
    SKY_BLUE("12", "Sky blue"),
    BLACK("13", "Black");

    private final String id;
    private final String name;

    Paint(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Paint findPaintById(String id) {
        for (Paint paint : values()) {
            if (paint.getId().equals(id)) {
                return paint;
            }
        }
        return NOT_KNOWN;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
