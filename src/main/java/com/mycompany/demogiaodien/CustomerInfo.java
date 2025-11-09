package com.mycompany.demogiaodien;

import java.util.List;

public class CustomerInfo {

    private final String id;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String membershipTier;
    private final List<Order> orders;

    public CustomerInfo(String id, String name, String phoneNumber, String email, String membershipTier, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.membershipTier = membershipTier;
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getMembershipTier() {
        return membershipTier;
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
