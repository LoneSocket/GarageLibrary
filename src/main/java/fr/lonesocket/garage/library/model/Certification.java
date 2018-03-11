package fr.lonesocket.garage.library.model;

public enum Certification {
    NOT_KNOWN("NK", "Not Known"),
    ANY("0", "Any"),
    NONE("N", "None"),
    PLAYMAKER("1", "Playmaker"),
    ACROBAT("2", "Acrobat"),
    AVIATOR("3", "Aviator"),
    GOALKEEPER("4", "Goalkeeper"),
    GUARDIAN("5", "Guardian"),
    JUGGLER("6", "Juggler"),
    PARAGON("7", "Paragon"),
    SCORER("8", "Scorer"),
    SHOWOFF("9", "Show-off"),
    SNIPER("10", "Sniper"),
    STRIKER("11", "Striker"),
    SWEEPER("12", "Sweeper"),
    TACTICIAN("13", "Tactician"),
    TURTLE("14", "Turtle"),
    VICTOR("15", "Victor");

    private final String id;
    private final String name;

    Certification(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Certification findCertificationById(String id) {
        for (Certification certification : values()) {
            if (certification.getId().equals(id)) {
                return certification;
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
