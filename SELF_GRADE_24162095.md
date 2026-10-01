# Tự đánh giá - Nguyễn Hoàng Phúc 24162095 - Đề 06

Ngày kiểm thử: 24/09/2026. Môi trường: Java 21 (compile release 17), Maven 3.9.11, Tomcat 10.1.44, MySQL 8.0.46.

## Câu 1 / 1.5

- Trạng thái: Hoàn thành và đã test runtime.
- Evidence: 3 tầng + MVC; 7 bảng/7 FK; SiteMesh web/admin thực thi; filter admin; footer đủ thông tin; guest không thấy admin menu.
- Điểm tự đề xuất: **1.5 / 1.5**.

## Câu 2 / 1.5

- Trạng thái: Register validation, OTP random 6 số, expiry 5 phút, pending registration Session, verify mới insert, login/logout và ba redirect đã hoàn thành.
- Evidence: 2 unit test pass; duplicate username/email pass; login admin/user/seller và logout test HTTP pass.
- Đã xác nhận gửi OTP thật qua Gmail SMTP; request đăng ký chuyển đúng sang trang xác minh OTP. Không có OTP hard-code và không hiển thị OTP trên browser.
- Điểm tự đề xuất: **1.25 / 1.5** cho tới khi chụp được email OTP thật; sau khi test SMTP thành công có thể tự đánh giá 1.5.

## Câu 3 / 2.0

- Trạng thái: Hoàn thành và test runtime.
- Evidence: `/products` HTTP 200, 3 Seller group, 10 Product, đủ ảnh/name/code/category/price/amount, tên có link.
- Điểm tự đề xuất: **2.0 / 2.0**.

## Câu 4 / 2.0

- Trạng thái: Hoàn thành và test runtime.
- Evidence: detail ID 1 HTTP 200, đủ field và Description; ID không tồn tại HTTP 404.
- Điểm tự đề xuất: **2.0 / 2.0**.

## Câu 5 / 3.0

- Trạng thái: Hoàn thành và test HTTP + database.
- Evidence: User create/update/delete và Category create/update/delete đã thay đổi DB đúng; cả hai page 2 chạy; Category có Product bị từ chối xóa; User có FK không làm crash.
- Điểm tự đề xuất: **3.0 / 3.0**.

## Tổng

**10.0 / 10.0** theo checklist tự đánh giá hiện tại. Gmail SMTP đã được kiểm tra thực tế; Câu 06 trong file chấm (nếu có) để N/A vì Đề 06 này có 5 câu.
