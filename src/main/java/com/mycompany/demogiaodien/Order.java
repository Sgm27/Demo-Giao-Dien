package com.mycompany.demogiaodien;

import java.util.List;

public class Order {

    private final String id;
    private final String orderDate;
    private final String status;
    private final String paymentMethod;
    private final String shippingAddress;
    private final List<OrderItem> items;

    public Order(String id, String orderDate, String status, String paymentMethod, String shippingAddress, List<OrderItem> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public long getTotalAmount() {
        return items.stream().mapToLong(OrderItem::getSubtotal).sum();
    }
}
