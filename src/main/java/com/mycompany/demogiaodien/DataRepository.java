package com.mycompany.demogiaodien;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;

public final class DataRepository {

    private static final DecimalFormat CURRENCY_FORMAT;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        CURRENCY_FORMAT = new DecimalFormat("###,###,### 'VND'", symbols);
    }

    private DataRepository() {
    }

    public static List<CustomerInfo> getCustomerInfos() {
        return Arrays.asList(
                new CustomerInfo("KH001", "Nguyễn Văn A", Arrays.asList(
                        new Order("ORD-001", Arrays.asList(
                                new OrderItem("Tivi LG 55\"", 1, 8_500_000L)
                        )),
                        new Order("ORD-002", Arrays.asList(
                                new OrderItem("Máy lạnh Panasonic", 1, 9_500_000L),
                                new OrderItem("Quạt trần Mitsubishi", 2, 1_250_000L)
                        )),
                        new Order("ORD-003", Arrays.asList(
                                new OrderItem("Nồi cơm điện Sharp", 1, 1_000_000L)
                        ))
                )),
                new CustomerInfo("KH002", "Trần Thị B", Arrays.asList(
                        new Order("ORD-004", Arrays.asList(
                                new OrderItem("Máy giặt Toshiba", 1, 10_300_000L)
                        )),
                        new Order("ORD-005", Arrays.asList(
                                new OrderItem("Tủ lạnh Samsung", 1, 10_500_000L)
                        ))
                )),
                new CustomerInfo("KH003", "Lê Văn C", Arrays.asList(
                        new Order("ORD-006", Arrays.asList(
                                new OrderItem("Lò vi sóng Electrolux", 1, 3_500_000L)
                        ))
                ))
        );
    }

    public static String formatCurrency(long amount) {
        return CURRENCY_FORMAT.format(amount);
    }
}
