package vn.hcmute.web24162095.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum OrderStatus_24162095 {
    CART("CART", "Giỏ hàng", "secondary", false),
    NEW("NEW", "Đơn hàng mới", "primary", true),
    CONFIRMED("CONFIRMED", "Đã xác nhận", "info", true),
    PREPARING("PREPARING", "Chuẩn bị hàng", "warning", true),
    IN_TRANSIT("IN_TRANSIT", "Vận chuyển", "info", true),
    OUT_FOR_DELIVERY("OUT_FOR_DELIVERY", "Giao hàng", "warning", true),
    DELIVERED("DELIVERED", "Đã giao", "success", true),
    CANCELLED("CANCELLED", "Đơn hàng hủy", "danger", true),
    RETURNED("RETURNED", "Đơn hàng hoàn", "secondary", true);

    private final String code;
    private final String label;
    private final String badgeClass;
    private final boolean orderStatus;

    OrderStatus_24162095(String code, String label, String badgeClass, boolean orderStatus) {
        this.code = code;
        this.label = label;
        this.badgeClass = badgeClass;
        this.orderStatus = orderStatus;
    }

    public String getCode() { return code; }
    public String getLabel() { return label; }
    public String getBadgeClass() { return badgeClass; }
    public boolean isOrderStatus() { return orderStatus; }

    public static Optional<OrderStatus_24162095> fromCode(String code) {
        if (code == null) return Optional.empty();
        return Arrays.stream(values()).filter(value -> value.code.equals(code)).findFirst();
    }

    public static List<OrderStatus_24162095> orderStatuses() {
        return Arrays.stream(values()).filter(OrderStatus_24162095::isOrderStatus).toList();
    }
}
