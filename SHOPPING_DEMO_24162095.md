# Script demo Cart, COD và Order History - 24162095

## Chuẩn bị

1. Chạy `database/migration_cart_order_24162095.sql` đúng một lần trên database cũ.
2. Build/deploy WAR lên Tomcat 10.1 và mở `http://localhost:8080/24162095_made/home`.
3. Login `user` / `Phuc@123`.

## Cart

1. Mở Sản phẩm, chọn một sản phẩm còn hàng và bấm **Thêm vào giỏ**.
2. Thêm chính sản phẩm đó lần nữa để chứng minh không tạo row trùng, quantity được cộng dồn.
3. Mở **Giỏ hàng**, bấm `+`, `−`, rồi nhập quantity trực tiếp và **Cập nhật**.
4. Nhập quantity lớn hơn stock để chụp thông báo từ chối.
5. Xóa một item, sau đó thêm lại để tiếp tục checkout.

## COD

1. Từ Cart bấm **Thanh toán COD**.
2. Nhập họ tên, số điện thoại dạng `0901234567`, địa chỉ và ghi chú.
3. Bấm **Xác nhận đặt hàng**, chụp màn hình thành công và mã đơn.
4. Trong Workbench chạy:

```sql
SELECT cartId,status,paymentMethod,totalAmount,receiverName,receiverPhone,shippingAddress
FROM Cart ORDER BY cartId DESC;

SELECT ci.cartId,ci.productId,ci.quantity,ci.unitPrice,p.stock
FROM CartItem ci JOIN Product p ON p.productId=ci.productId
WHERE ci.cartId=<MA_DON>;
```

5. Chứng minh Cart là `NEW`, payment `COD`, total đúng, CartItem còn và stock đã giảm.

## Order History và status filter

1. Mở `/orders`, xem order `NEW` và mở chi tiết.
2. Mở `database/order_status_demo_24162095.sql`, thay `@order_id` bằng mã đơn.
3. Chạy từng UPDATE một; sau mỗi lần refresh browser và chọn filter tương ứng:
   `NEW`, `CONFIRMED`, `PREPARING`, `IN_TRANSIT`, `OUT_FOR_DELIVERY`, `DELIVERED`, `CANCELLED`, `RETURNED`.
4. Thử `/orders?status=HACKED`: trang fallback danh sách tất cả, không lỗi SQL.
5. Login tài khoản User khác (ví dụ `seller1`) rồi mở `/order/detail?id=<MA_DON>`: kết quả 404.

Không chạy toàn bộ các UPDATE trạng thái cùng lúc nếu đang chụp từng bước. Không chụp file chứa DB/SMTP password.
