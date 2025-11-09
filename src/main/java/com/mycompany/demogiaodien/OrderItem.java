package com.mycompany.demogiaodien;

public class OrderItem {

    private final String productName;
    private final int quantity;
    private final long unitPrice;

    public OrderItem(String productName, int quantity, long unitPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public long getSubtotal() {
        return unitPrice * quantity;
    }
}
