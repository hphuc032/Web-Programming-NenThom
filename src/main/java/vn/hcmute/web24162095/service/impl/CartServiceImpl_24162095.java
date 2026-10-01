package vn.hcmute.web24162095.service.impl;

import vn.hcmute.web24162095.dao.CartDAO_24162095;
import vn.hcmute.web24162095.dao.impl.CartDAOImpl_24162095;
import vn.hcmute.web24162095.model.CartItem_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.model.Product_24162095;
import vn.hcmute.web24162095.service.CartException_24162095;
import vn.hcmute.web24162095.service.CartService_24162095;
import vn.hcmute.web24162095.service.ProductService_24162095;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceImpl_24162095 implements CartService_24162095 {
    private final CartDAO_24162095 cartDAO;
    private final ProductService_24162095 productService;

    public CartServiceImpl_24162095() {
        this(new CartDAOImpl_24162095(), new ProductServiceImpl_24162095());
    }

    public CartServiceImpl_24162095(CartDAO_24162095 cartDAO, ProductService_24162095 productService) {
        this.cartDAO = cartDAO;
        this.productService = productService;
    }

    @Override
    public Cart_24162095 getOrCreateCart(int userId) {
        requireUser(userId);
        return cartDAO.findActiveCartByUserId(userId).orElseGet(() -> cartDAO.createCart(userId));
    }

    @Override
    public List<CartItem_24162095> getItems(int userId) {
        return cartDAO.findItemsByCartId(getOrCreateCart(userId).getCartId());
    }

    @Override
    public BigDecimal getTotal(int userId) {
        return cartDAO.calculateTotal(getOrCreateCart(userId).getCartId());
    }

    @Override
    public int getTotalQuantity(int userId) {
        return cartDAO.countItems(getOrCreateCart(userId).getCartId());
    }

    @Override
    public void addProduct(int userId, int productId, int quantity) {
        validateRequestedQuantity(quantity);
        Product_24162095 product = requireAvailableProduct(productId);
        Cart_24162095 cart = getOrCreateCart(userId);
        CartItem_24162095 existing = cartDAO.findItem(cart.getCartId(), productId).orElse(null);
        long newQuantity = (long) quantity + (existing == null ? 0L : existing.getQuantity());
        validateStock(newQuantity, product.getStock());
        if (existing == null) {
            cartDAO.addItem(cart.getCartId(), productId, quantity, product.getPrice());
        } else {
            cartDAO.updateQuantity(cart.getCartId(), productId, (int) newQuantity);
        }
    }

    @Override
    public void updateQuantity(int userId, int productId, int quantity) {
        validateRequestedQuantity(quantity);
        Product_24162095 product = requireAvailableProduct(productId);
        Cart_24162095 cart = requireActiveCart(userId);
        requireItem(cart.getCartId(), productId);
        validateStock(quantity, product.getStock());
        cartDAO.updateQuantity(cart.getCartId(), productId, quantity);
    }

    @Override
    public void adjustQuantity(int userId, int productId, int delta) {
        if (delta != -1 && delta != 1) throw new CartException_24162095("Thao tác số lượng không hợp lệ.");
        Product_24162095 product = requireAvailableProduct(productId);
        Cart_24162095 cart = requireActiveCart(userId);
        CartItem_24162095 item = requireItem(cart.getCartId(), productId);
        long newQuantity = (long) item.getQuantity() + delta;
        validateRequestedQuantity(newQuantity);
        validateStock(newQuantity, product.getStock());
        cartDAO.updateQuantity(cart.getCartId(), productId, (int) newQuantity);
    }

    @Override
    public void removeProduct(int userId, int productId) {
        Cart_24162095 cart = requireActiveCart(userId);
        requireItem(cart.getCartId(), productId);
        cartDAO.removeItem(cart.getCartId(), productId);
    }

    @Override
    public void clearCart(int userId) {
        cartDAO.findActiveCartByUserId(userId).ifPresent(cart -> cartDAO.clearCart(cart.getCartId()));
    }

    private Product_24162095 requireAvailableProduct(int productId) {
        if (productId <= 0) throw new CartException_24162095("Sản phẩm không hợp lệ.");
        Product_24162095 product = productService.getById(productId)
                .orElseThrow(() -> new CartException_24162095("Không tìm thấy sản phẩm."));
        if (!product.isStatus()) throw new CartException_24162095("Sản phẩm hiện không còn bán.");
        if (product.getStock() <= 0) throw new CartException_24162095("Sản phẩm hiện đã hết hàng.");
        return product;
    }

    private Cart_24162095 requireActiveCart(int userId) {
        requireUser(userId);
        return cartDAO.findActiveCartByUserId(userId)
                .orElseThrow(() -> new CartException_24162095("Giỏ hàng không tồn tại."));
    }

    private CartItem_24162095 requireItem(int cartId, int productId) {
        return cartDAO.findItem(cartId, productId)
                .orElseThrow(() -> new CartException_24162095("Sản phẩm không có trong giỏ hàng."));
    }

    private void validateRequestedQuantity(long quantity) {
        if (quantity < 1 || quantity > Integer.MAX_VALUE) {
            throw new CartException_24162095("Số lượng phải là số nguyên từ 1 trở lên.");
        }
    }

    private void validateStock(long quantity, int stock) {
        if (quantity > stock) {
            throw new CartException_24162095("Số lượng vượt quá tồn kho. Sản phẩm hiện chỉ còn " + stock + ".");
        }
    }

    private void requireUser(int userId) {
        if (userId <= 0) throw new CartException_24162095("Tài khoản không hợp lệ.");
    }
}
