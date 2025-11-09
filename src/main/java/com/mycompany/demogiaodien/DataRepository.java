package com.mycompany.demogiaodien;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
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
                new CustomerInfo("KH001", "Nguyễn Văn A", Arrays.asList(8_500_000L, 12_000_000L, 1_000_000L)),
                new CustomerInfo("KH002", "Trần Thị B", Arrays.asList(10_300_000L, 10_500_000L)),
                new CustomerInfo("KH003", "Lê Văn C", Collections.singletonList(3_500_000L))
        );
    }

    public static String formatCurrency(long amount) {
        return CURRENCY_FORMAT.format(amount);
    }
}
