package vn.hcmute.web24162095.model;

import java.math.BigDecimal;

public class CartItem_24162095 {
    private int cartItemId;
    private int quantity;
    private BigDecimal unitPrice;
    private int productId;
    private int cartId;
    private String productName;
    private String productImage;
    private int availableStock;
    private boolean productActive;
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
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
    public int getAvailableStock() { return availableStock; }
    public void setAvailableStock(int availableStock) { this.availableStock = availableStock; }
    public boolean isProductActive() { return productActive; }
    public void setProductActive(boolean productActive) { this.productActive = productActive; }
    public BigDecimal getSubtotal() { return unitPrice == null ? BigDecimal.ZERO : unitPrice.multiply(BigDecimal.valueOf(quantity)); }
}
