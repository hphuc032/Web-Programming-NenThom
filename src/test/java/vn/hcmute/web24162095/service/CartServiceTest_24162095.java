package vn.hcmute.web24162095.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.hcmute.web24162095.dao.CartDAO_24162095;
import vn.hcmute.web24162095.model.*;
import vn.hcmute.web24162095.service.impl.CartServiceImpl_24162095;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest_24162095 {
    private FakeCartDAO cartDAO;
    private Product_24162095 product;
    private CartService_24162095 service;

    @BeforeEach
    void setUp() {
        cartDAO = new FakeCartDAO();
        product = new Product_24162095();
        product.setProductId(1);
        product.setProductName("Nến thơm");
        product.setPrice(new BigDecimal("120000"));
        product.setStock(5);
        product.setStatus(true);
        ProductService_24162095 productService = new ProductService_24162095() {
            @Override public Map<Seller_24162095, List<Product_24162095>> getProductsGroupedBySeller() { return Map.of(); }
            @Override public Optional<Product_24162095> getById(int id) { return id == 1 ? Optional.of(product) : Optional.empty(); }
        };
        service = new CartServiceImpl_24162095(cartDAO, productService);
    }

    @Test
    void duplicateProductAccumulatesQuantityAndKeepsPriceSnapshot() {
        service.addProduct(2, 1, 1);
        product.setPrice(new BigDecimal("999000"));
        service.addProduct(2, 1, 2);

        CartItem_24162095 item = cartDAO.item;
        assertEquals(3, item.getQuantity());
        assertEquals(new BigDecimal("120000"), item.getUnitPrice());
        assertEquals(new BigDecimal("360000"), cartDAO.calculateTotal(cartDAO.cart.getCartId()));
    }

    @Test
    void addAndUpdateRejectInvalidOrOverStockQuantity() {
        service.addProduct(2, 1, 3);
        CartException_24162095 overflow = assertThrows(CartException_24162095.class,
                () -> service.addProduct(2, 1, 4));
        assertTrue(overflow.getMessage().contains("chỉ còn 5"));
        assertEquals(3, cartDAO.item.getQuantity());
        assertThrows(CartException_24162095.class, () -> service.updateQuantity(2, 1, 0));
        assertThrows(CartException_24162095.class, () -> service.updateQuantity(2, 1, 999));
    }

    @Test
    void inactiveAndOutOfStockProductsCannotBeAdded() {
        product.setStatus(false);
        assertThrows(CartException_24162095.class, () -> service.addProduct(2, 1, 1));
        product.setStatus(true);
        product.setStock(0);
        assertThrows(CartException_24162095.class, () -> service.addProduct(2, 1, 1));
        assertNull(cartDAO.item);
    }

    private static class FakeCartDAO implements CartDAO_24162095 {
        private Cart_24162095 cart;
        private CartItem_24162095 item;

        @Override public Optional<Cart_24162095> findActiveCartByUserId(int userId) { return Optional.ofNullable(cart); }
        @Override public Cart_24162095 createCart(int userId) {
            cart = new Cart_24162095();
            cart.setCartId(1);
            cart.setUserId(userId);
            cart.setStatus("CART");
            return cart;
        }
        @Override public List<CartItem_24162095> findItemsByCartId(int cartId) { return item == null ? List.of() : List.of(item); }
        @Override public Optional<CartItem_24162095> findItem(int cartId, int productId) { return Optional.ofNullable(item); }
        @Override public void addItem(int cartId, int productId, int quantity, BigDecimal unitPrice) {
            item = new CartItem_24162095();
            item.setCartId(cartId);
            item.setProductId(productId);
            item.setQuantity(quantity);
            item.setUnitPrice(unitPrice);
        }
        @Override public void updateQuantity(int cartId, int productId, int quantity) { item.setQuantity(quantity); }
        @Override public void removeItem(int cartId, int productId) { item = null; }
        @Override public void clearCart(int cartId) { item = null; }
        @Override public BigDecimal calculateTotal(int cartId) { return item == null ? BigDecimal.ZERO : item.getSubtotal(); }
        @Override public int countItems(int cartId) { return item == null ? 0 : item.getQuantity(); }
    }
}
