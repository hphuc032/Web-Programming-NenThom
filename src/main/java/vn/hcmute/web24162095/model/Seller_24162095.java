package vn.hcmute.web24162095.model;

import java.util.Objects;

public class Seller_24162095 {
    private Integer sellerId;
    private String sellerName;
    private String images;
    private boolean status;
    public Integer getSellerId() { return sellerId; }
    public void setSellerId(Integer sellerId) { this.sellerId = sellerId; }
    public String getSellerName() { return sellerName; }
    public void setSellerName(String sellerName) { this.sellerName = sellerName; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    @Override public boolean equals(Object o) { return o instanceof Seller_24162095 s && Objects.equals(sellerId, s.sellerId); }
    @Override public int hashCode() { return Objects.hashCode(sellerId); }
}
