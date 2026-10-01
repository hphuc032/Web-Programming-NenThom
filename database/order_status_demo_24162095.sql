USE web24162095;

-- Thay số 1 bằng cartId của đơn cần demo (không dùng cart status=CART).
SET @order_id = 1;

UPDATE Cart SET status = 'NEW' WHERE cartId = @order_id AND status <> 'CART';
UPDATE Cart SET status = 'CONFIRMED' WHERE cartId = @order_id AND status <> 'CART';
UPDATE Cart SET status = 'PREPARING' WHERE cartId = @order_id AND status <> 'CART';
UPDATE Cart SET status = 'IN_TRANSIT' WHERE cartId = @order_id AND status <> 'CART';
UPDATE Cart SET status = 'OUT_FOR_DELIVERY' WHERE cartId = @order_id AND status <> 'CART';
UPDATE Cart SET status = 'DELIVERED' WHERE cartId = @order_id AND status <> 'CART';
UPDATE Cart SET status = 'CANCELLED' WHERE cartId = @order_id AND status <> 'CART';
UPDATE Cart SET status = 'RETURNED' WHERE cartId = @order_id AND status <> 'CART';

-- Chỉ chạy MỘT lệnh UPDATE trạng thái mỗi lần, sau đó refresh /orders để quan sát.
-- Việc đổi CANCELLED/RETURNED thủ công phục vụ demo và không tự cộng lại stock.
