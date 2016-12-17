package com.example.dnp.demoxml.dom;

/**
 * Created by DNP on 12/14/2016.
 */

public class ItemDetail {
    private String number;
    private String product;
    private String quantity;
    private String price;

    public ItemDetail(String number, String product, String quantity, String price) {
        this.number = number;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuatity() {
        return quantity;
    }

    public void setQuatity(String quatity) {
        this.quantity = quatity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
