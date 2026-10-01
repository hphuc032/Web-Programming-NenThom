package vn.hcmute.web24162095.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cart_24162095 {
    private int cartId;
    private int userId;
    private LocalDateTime buyDate;
    private String status;
    private String receiverName;
    private String receiverPhone;
    private String shippingAddress;
    private String paymentMethod;
    private BigDecimal totalAmount;
    private String note;
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public LocalDateTime getBuyDate() { return buyDate; }
    public void setBuyDate(LocalDateTime buyDate) { this.buyDate = buyDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }
    public String getReceiverPhone() { return receiverPhone; }
    public void setReceiverPhone(String receiverPhone) { this.receiverPhone = receiverPhone; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
