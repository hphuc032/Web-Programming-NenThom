package vn.hcmute.web24162095.service.impl;

import vn.hcmute.web24162095.dao.CheckoutDAO_24162095;
import vn.hcmute.web24162095.dao.impl.CheckoutDAOImpl_24162095;
import vn.hcmute.web24162095.model.Cart_24162095;
import vn.hcmute.web24162095.service.CheckoutException_24162095;
import vn.hcmute.web24162095.service.CheckoutService_24162095;

public class CheckoutServiceImpl_24162095 implements CheckoutService_24162095 {
    private final CheckoutDAO_24162095 checkoutDAO;

    public CheckoutServiceImpl_24162095() {
        this(new CheckoutDAOImpl_24162095());
    }

    public CheckoutServiceImpl_24162095(CheckoutDAO_24162095 checkoutDAO) {
        this.checkoutDAO = checkoutDAO;
    }

    @Override
    public Cart_24162095 checkoutCod(int userId, String receiverName, String receiverPhone,
                                    String shippingAddress, String note) {
        if (userId <= 0) throw new CheckoutException_24162095("Tài khoản không hợp lệ.");
        String name = normalize(receiverName);
        String phone = normalize(receiverPhone).replace(" ", "").replace("-", "");
        String address = normalize(shippingAddress);
        String normalizedNote = normalize(note);

        if (name.isEmpty()) throw new CheckoutException_24162095("Vui lòng nhập họ tên người nhận.");
        if (name.length() > 160) throw new CheckoutException_24162095("Họ tên người nhận quá dài.");
        if (!phone.matches("^(0\\d{9}|\\+84\\d{9})$")) {
            throw new CheckoutException_24162095("Số điện thoại phải có dạng 0xxxxxxxxx hoặc +84xxxxxxxxx.");
        }
        if (address.isEmpty()) throw new CheckoutException_24162095("Vui lòng nhập địa chỉ giao hàng.");
        if (address.length() > 500) throw new CheckoutException_24162095("Địa chỉ giao hàng quá dài.");
        if (normalizedNote.length() > 500) throw new CheckoutException_24162095("Ghi chú không được vượt quá 500 ký tự.");

        return checkoutDAO.checkoutCod(userId, name, phone, address,
                normalizedNote.isEmpty() ? null : normalizedNote);
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
