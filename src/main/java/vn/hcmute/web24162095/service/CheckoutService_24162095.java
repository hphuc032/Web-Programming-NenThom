package vn.hcmute.web24162095.service;

import vn.hcmute.web24162095.model.Cart_24162095;

public interface CheckoutService_24162095 {
    Cart_24162095 checkoutCod(int userId, String receiverName, String receiverPhone,
                             String shippingAddress, String note);
}
