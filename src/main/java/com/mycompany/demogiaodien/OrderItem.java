package com.mycompany.demogiaodien;

public class OrderItem {

    private final String productCode;
    private final String productName;
    private final String category;
    private final int quantity;
    private final long unitPrice;

    public OrderItem(String productCode, String productName, String category, int quantity, long unitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
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
