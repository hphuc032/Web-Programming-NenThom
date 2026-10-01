package vn.hcmute.web24162095.service;

import org.junit.jupiter.api.Test;
import vn.hcmute.web24162095.dao.CheckoutDAO_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.service.impl.CheckoutServiceImpl_24162095;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest_24162095 {
    @Test
    void validatesRequiredDeliveryFieldsAndPhone() {
        CheckoutService_24162095 service = new CheckoutServiceImpl_24162095(new FakeCheckoutDAO());
        assertThrows(CheckoutException_24162095.class,
                () -> service.checkoutCod(2, "", "0901234567", "TP.HCM", ""));
        assertThrows(CheckoutException_24162095.class,
                () -> service.checkoutCod(2, "Nguyễn Văn A", "abc", "TP.HCM", ""));
        assertThrows(CheckoutException_24162095.class,
                () -> service.checkoutCod(2, "Nguyễn Văn A", "0901234567", "", ""));
    }

    @Test
    void normalizesInputAndAlwaysUsesCodDaoFlow() {
        FakeCheckoutDAO dao = new FakeCheckoutDAO();
        CheckoutService_24162095 service = new CheckoutServiceImpl_24162095(dao);
        Cart_24162095 order = service.checkoutCod(2, "  Nguyễn Văn A  ", "0901 234 567",
                "  01 Võ Văn Ngân  ", "  Giao giờ hành chính  ");
        assertEquals("Nguyễn Văn A", dao.name);
        assertEquals("0901234567", dao.phone);
        assertEquals("01 Võ Văn Ngân", dao.address);
        assertEquals("Giao giờ hành chính", dao.note);
        assertEquals("COD", order.getPaymentMethod());
    }

    private static class FakeCheckoutDAO implements CheckoutDAO_24162095 {
        String name;
        String phone;
        String address;
        String note;

        @Override
        public Cart_24162095 checkoutCod(int userId, String receiverName, String receiverPhone,
                                        String shippingAddress, String note) {
            this.name = receiverName;
            this.phone = receiverPhone;
            this.address = shippingAddress;
            this.note = note;
            Cart_24162095 order = new Cart_24162095();
            order.setCartId(10);
            order.setUserId(userId);
            order.setStatus("NEW");
            order.setPaymentMethod("COD");
            return order;
        }
    }
}
