package com.mycompany.demogiaodien;

import java.util.List;

public class CustomerInfo {

    private final String id;
    private final String name;
    private final List<Long> transactionAmounts;

    public CustomerInfo(String id, String name, List<Long> transactionAmounts) {
        this.id = id;
        this.name = name;
        this.transactionAmounts = transactionAmounts;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getTransactionAmounts() {
        return transactionAmounts;
    }

    public int getTransactionCount() {
        return transactionAmounts.size();
    }

    public long getTotalRevenue() {
        return transactionAmounts.stream().mapToLong(Long::longValue).sum();
    }
}
