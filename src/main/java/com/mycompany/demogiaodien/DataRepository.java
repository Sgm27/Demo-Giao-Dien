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
                new CustomerInfo("KH001", "Nguyễn Văn A", "0901 234 567", "vana.nguyen@example.com", "Vàng", Arrays.asList(
                        new Order("DH001", "28/09/2025", "Hoàn tất", "Chuyển khoản", "123 Nguyễn Trãi, Hà Nội", Arrays.asList(
                                new OrderItem("SP001", "Tivi LG 55\"", "Điện tử", 1, 8_500_000L),
                                new OrderItem("SP002", "Loa Soundbar Sony HT-S350", "Điện tử", 1, 4_250_000L)
                        )),
                        new Order("DH002", "01/10/2025", "Đang giao", "Tiền mặt", "123 Nguyễn Trãi, Hà Nội", Arrays.asList(
                                new OrderItem("SP010", "Máy lạnh Panasonic Inverter", "Điện máy", 1, 9_500_000L),
                                new OrderItem("SP011", "Quạt trần Mitsubishi C56-RW4", "Điện máy", 2, 1_250_000L)
                        )),
                        new Order("DH003", "02/10/2025", "Hoàn tất", "Ví điện tử", "123 Nguyễn Trãi, Hà Nội", Arrays.asList(
                                new OrderItem("SP020", "Nồi cơm điện Sharp KS-TH18", "Gia dụng", 1, 1_000_000L),
                                new OrderItem("SP021", "Bộ nồi inox Sunhouse SH999", "Gia dụng", 1, 1_450_000L)
                        ))
                )),
                new CustomerInfo("KH002", "Trần Thị B", "0987 654 321", "thib.tran@example.com", "Bạc", Arrays.asList(
                        new Order("DH004", "25/09/2025", "Hoàn tất", "Chuyển khoản", "45 Lê Duẩn, Đà Nẵng", Arrays.asList(
                                new OrderItem("SP030", "Máy giặt Toshiba Inverter", "Điện máy", 1, 10_300_000L),
                                new OrderItem("SP031", "Nước giặt Ariel 3.6kg", "Tiêu dùng", 2, 320_000L)
                        )),
                        new Order("DH005", "30/09/2025", "Hoàn tất", "Thẻ tín dụng", "45 Lê Duẩn, Đà Nẵng", Arrays.asList(
                                new OrderItem("SP040", "Tủ lạnh Samsung Family Hub", "Điện máy", 1, 32_900_000L)
                        ))
                )),
                new CustomerInfo("KH003", "Lê Văn C", "0912 888 999", "vanc.le@example.com", "Kim cương", Arrays.asList(
                        new Order("DH006", "27/09/2025", "Chờ xác nhận", "Thẻ ghi nợ", "78 Hai Bà Trưng, TP.HCM", Arrays.asList(
                                new OrderItem("SP050", "Lò vi sóng Electrolux EMS3085", "Gia dụng", 1, 3_500_000L),
                                new OrderItem("SP051", "Bếp hồng ngoại Midea MIR-T2015", "Gia dụng", 1, 1_200_000L)
                        ))
                ))
        );
    }

    public static String formatCurrency(long amount) {
        return CURRENCY_FORMAT.format(amount);
    }
}
