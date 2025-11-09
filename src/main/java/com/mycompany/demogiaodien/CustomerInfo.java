package com.mycompany.demogiaodien;

import java.util.List;

public class CustomerInfo {

    private final String id;
    private final String name;
    private final List<Order> orders;

    public CustomerInfo(String id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getTransactionCount() {
        return orders.size();
    }

    public long getTotalRevenue() {
        return orders.stream().mapToLong(Order::getTotalAmount).sum();
    }
}
