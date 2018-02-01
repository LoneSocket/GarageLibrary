package fr.lonesocket.garage.library.model;

public class Item {
    private final int id;
    private final int certificationId;
    private final int paintId;
    private final String imgUrl;
    private final int quantity;

    public Item(int id, int certificationId, int paintId, String imgUrl, int quantity) {
        this.id = id;
        this.certificationId = certificationId;
        this.paintId = paintId;
        this.imgUrl = imgUrl;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getId() {
        return id;
    }
}
