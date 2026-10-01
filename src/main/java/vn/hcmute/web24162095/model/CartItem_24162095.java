package vn.hcmute.web24162095.model;

import java.math.BigDecimal;

public class CartItem_24162095 {
    private int cartItemId;
    private int quantity;
    private BigDecimal unitPrice;
    private int productId;
    private int cartId;
    public int getCartItemId() { return cartItemId; }
    public void setCartItemId(int cartItemId) { this.cartItemId = cartItemId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
}
