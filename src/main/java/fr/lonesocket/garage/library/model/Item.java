package fr.lonesocket.garage.library.model;

public class Item extends ReferenceItem {
    private final Certification certification;
    private final Paint paint;
    private final String imgUrl;
    private final int quantity;

    public Item(String id, String name, Certification certification, Paint paint, String imgUrl, int quantity) {
        super(id, name);
        this.certification = certification;
        this.paint = paint;
        this.imgUrl = imgUrl;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Certification getCertification() {
        return certification;
    }

    public Paint getPaint() {
        return paint;
    }
}
