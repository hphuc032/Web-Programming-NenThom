-- Migration an toàn cho database web24162095 hiện có.
-- Đã kiểm tra dữ liệu trước migration: status hiện dùng 0 cho giỏ và 1 cho đơn.
USE web24162095;

ALTER TABLE Cart
    MODIFY COLUMN status VARCHAR(30) NOT NULL DEFAULT 'CART';

UPDATE Cart SET status = 'CART' WHERE status = '0';
UPDATE Cart SET status = 'NEW' WHERE status = '1';

ALTER TABLE Cart
    ADD COLUMN receiverName VARCHAR(160) NULL AFTER status,
    ADD COLUMN receiverPhone VARCHAR(30) NULL AFTER receiverName,
    ADD COLUMN shippingAddress VARCHAR(500) NULL AFTER receiverPhone,
    ADD COLUMN paymentMethod VARCHAR(30) NULL AFTER shippingAddress,
    ADD COLUMN totalAmount DECIMAL(15,2) NULL AFTER paymentMethod,
    ADD COLUMN note VARCHAR(500) NULL AFTER totalAmount;

-- Không DROP bảng và không xóa CartItem. Cart status=CART là giỏ đang mua;
-- sau checkout chính Cart/CartItem đó trở thành đơn hàng.
