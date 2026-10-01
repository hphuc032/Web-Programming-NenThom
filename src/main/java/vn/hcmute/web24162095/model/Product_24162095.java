package vn.hcmute.web24162095.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product_24162095 {
    private int productId;
    private String productName;
    private String productCode;
    private int categoryId;
    private String categoryName;
    private String description;
    private BigDecimal price;
    private int amount;
    private int stock;
    private String images;
    private int wishlist;
    private boolean status;
    private LocalDateTime createDate;
    private int sellerId;
    private String sellerName;
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public int getWishlist() { return wishlist; }
    public void setWishlist(int wishlist) { this.wishlist = wishlist; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }
    public int getSellerId() { return sellerId; }
    public void setSellerId(int sellerId) { this.sellerId = sellerId; }
    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }
}
