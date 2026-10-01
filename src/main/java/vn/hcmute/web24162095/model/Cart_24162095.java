package vn.hcmute.web24162095.model;

import java.time.LocalDateTime;

public class Cart_24162095 {
    private int cartId;
    private int userId;
    private LocalDateTime buyDate;
    private boolean status;
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public LocalDateTime getBuyDate() { return buyDate; }
    public void setBuyDate(LocalDateTime buyDate) { this.buyDate = buyDate; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
}
