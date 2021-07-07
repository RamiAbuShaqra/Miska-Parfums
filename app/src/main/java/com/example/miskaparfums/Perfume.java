package com.example.miskaparfums;

public class Perfume {
    private final int imageResourceID;
    private final int captionResourceID;
    private double productPrice;
    private int quantity;

    public Perfume(int imageResourceID, int captionResourceID) {
        this.imageResourceID = imageResourceID;
        this.captionResourceID = captionResourceID;
    }

    public Perfume(int imageResourceID, int captionResourceID, double productPrice) {
        this.imageResourceID = imageResourceID;
        this.captionResourceID = captionResourceID;
        this.productPrice = productPrice;
    }

    public Perfume(int imageResourceID, int captionResourceID, double productPrice, int quantity) {
        this.imageResourceID = imageResourceID;
        this.captionResourceID = captionResourceID;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public int getImageResourceID() {return imageResourceID;}
    public int getCaptionResourceID() {return captionResourceID;}
    public double getProductPrice() {return productPrice;}
    public int getQuantity() {return quantity;}
}
