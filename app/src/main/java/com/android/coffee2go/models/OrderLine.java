package com.android.coffee2go.models;

/**
 * @author Michal Pupák
 * **/
public class OrderLine {
    private MenuItem product;
    private int quantity;
    private String comment;

    public OrderLine(MenuItem product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        comment = "";
    }

    public double getTotal(){
        return product.getTotal()*quantity;
    }

    public MenuItem getProduct() {
        return product;
    }

    public void setProduct(MenuItem product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "product=" + product.getName() +
                ", quantity=" + quantity +
                ", comment='" + comment + '\'' +
                '}';
    }
}
