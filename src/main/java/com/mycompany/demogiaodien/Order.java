package com.mycompany.demogiaodien;

import java.util.List;

public class Order {

    private final String id;
    private final List<OrderItem> items;

    public Order(String id, List<OrderItem> items) {
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public long getTotalAmount() {
        return items.stream().mapToLong(OrderItem::getSubtotal).sum();
    }
}
