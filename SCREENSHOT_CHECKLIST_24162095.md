# Checklist ảnh chụp toàn màn hình - Nguyễn Hoàng Phúc 24162095 - Đề 06

Chỉ dùng ảnh chụp thật. Mỗi ảnh phải chụp toàn màn hình, thấy rõ IDE/browser, filename hoặc URL, không crop một đoạn code nhỏ. Trong báo cáo dùng nhãn “Repository / DAO”.

## Câu 1 - 1.5 điểm

- [ ] Full IDE project tree: `model`, `dao`, `service`, `controller`, `filter`, `views`, `decorators`.
- [ ] MySQL Workbench: database `web24162095`, đủ 7 bảng và sơ đồ/foreign key.
- [ ] Package Repository / DAO và một implementation dùng JDBC `PreparedStatement`.
- [ ] Package Service và một ServiceImpl.
- [ ] Package Controller.
- [ ] `WEB-INF/sitemesh3.xml`.
- [ ] `WEB-INF/decorators/web.jsp`.
- [ ] `WEB-INF/decorators/admin.jsp`.
- [ ] Browser `/home` khi chưa login, không có “Trang quản trị”.
- [ ] Browser `/admin/home` sau login admin, có admin menu.
- [ ] Footer thấy đủ Nguyễn Hoàng Phúc, 24162095, Mã đề 06.

## Câu 2 - 1.5 điểm

- [ ] `UserDAO_24162095` và `UserDAOImpl_24162095`.
- [ ] `UserService_24162095` và `UserServiceImpl_24162095`.
- [ ] `RegisterController_24162095`.
- [ ] `VerifyOtpController_24162095`.
- [ ] `LoginController_24162095` và `LogoutController_24162095`.
- [ ] `register.jsp`, `verify-otp.jsp`, `login.jsp`.
- [x] Email OTP thật sau khi đã cấu hình SMTP (không chụp App Password).
- [ ] Register success sau verify OTP.
- [ ] Login admin → `/admin/home`.
- [ ] Login user → `/home`.
- [ ] Login seller → `/seller/home`.
- [ ] Logout → `/login`.

## Câu 3 - 2 điểm

- [ ] `ProductDAOImpl_24162095.findAllOrderedBySeller` và JOIN Category/Seller.
- [ ] `ProductServiceImpl_24162095.getProductsGroupedBySeller`.
- [ ] `ProductListController_24162095`.
- [ ] `views/web/products.jsp`.
- [ ] Browser `/products`: thấy ít nhất 3 dòng “Mã cửa hàng”, ảnh và 10 sản phẩm.

## Câu 4 - 2 điểm

- [ ] `ProductDAOImpl_24162095.getById`.
- [ ] `ProductService_24162095.getById`.
- [ ] `ProductDetailController_24162095`.
- [ ] `views/web/product-detail.jsp`.
- [ ] Browser `/product/detail?id=1`: ảnh, Name, Code, Category, Price, Amount, Description.
- [ ] Browser ID không tồn tại: trang 404 hợp lý.

## Câu 5 - 3 điểm

### User CRUD

- [ ] User DAO CRUD + `LIMIT/OFFSET` + `count`.
- [ ] User Service CRUD và validation.
- [ ] Bốn User Controller.
- [ ] JSP User list và form add/edit.
- [ ] Browser thực hiện add, edit, delete.
- [ ] `/admin/users?page=2&size=5` thấy pagination.

### Category CRUD

- [ ] Category DAO CRUD + `LIMIT/OFFSET` + `count`.
- [ ] Category Service.
- [ ] Bốn Category Controller.
- [ ] JSP Category list và form add/edit.
- [ ] Browser thực hiện add, edit, delete.
- [ ] `/admin/categories?page=2&size=5` thấy pagination.
- [ ] Thử xóa Category đang có Product và chụp thông báo từ chối.

## Trước khi nộp

- [ ] Không để password DB/SMTP trong ảnh.
- [ ] Chèn ảnh thật vào đúng placeholder trong `24162095.docx`.
- [ ] Cập nhật trạng thái SMTP trong README/self-grade nếu đã test gửi thật.
- [ ] Mở lại ZIP và kiểm tra source build được.
