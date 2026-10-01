# Nguyễn Hoàng Phúc - 24162095 - Đề 06

Project thi quá trình môn Lập Trình Web, xây dựng bằng Servlet/JSP/JSTL/JDBC theo MVC và kiến trúc ba tầng. Artifact Maven là WAR, không dùng Spring, JPA, Hibernate, Thymeleaf hay REST.

## Technologies

- Java source/target 17 (đã build bằng JDK 21)
- Maven 3.9.11, packaging `war`
- Jakarta Servlet 6, JSP 3.1, JSTL 3
- SiteMesh 3.2.1
- JDBC MySQL Connector/J 8.4
- MySQL 8.0.46
- Jakarta Mail (Angus) và BCrypt
- Apache Tomcat 10.1.44

Tomcat 10 dùng namespace `jakarta.servlet.*`, nên project dùng SiteMesh 3.2.x (nhánh Jakarta EE 9/10) nhất quán. Decorator mapping thật nằm tại `WEB-INF/sitemesh3.xml`; hai layout nằm tại `WEB-INF/decorators/web.jsp` và `admin.jsp`.

## Architecture

Luồng thực thi: Browser → JSP → Servlet Controller → Service → DAO → JDBC → MySQL.

- Presentation: `controller`, `filter`, JSP và SiteMesh decorators.
- Business: interface/implementation trong `service`.
- Data access: interface/implementation trong `dao`, kết nối tại `DBConnection_24162095`.
- Domain: các JavaBean trong `model`.

Controller chỉ gọi Service; JSP không chứa SQL/DAO; mọi input SQL dùng `PreparedStatement`; JDBC resource dùng try-with-resources.

## Database setup

MySQL scripts:

1. Chạy `database/schema_24162095.sql`.
2. Chạy `database/sample-data_24162095.sql`.

Với database cũ đã có dữ liệu, không chạy lại schema. Chỉ chạy `database/migration_cart_order_24162095.sql`. Migration không drop bảng: đổi `Cart.status` từ boolean sang mã trạng thái, ánh xạ `0 → CART`, `1 → NEW`, rồi thêm thông tin nhận hàng/COD.

Database `web24162095` có 7 bảng: `UserRoles`, `Users`, `Seller`, `Category`, `Product`, `Cart`, `CartItem`; 7 foreign key và mọi primary key ID đều `AUTO_INCREMENT`.

`Product.stock` là tồn kho thực tế được kiểm tra và trừ trong transaction checkout. `Product.amount` được giữ nguyên để tương thích dữ liệu/đề cũ và không bị trừ song song.

Đề minh họa `cartId/cartItemId` dạng text nhưng đồng thời yêu cầu tất cả ID tăng tự động; project ưu tiên yêu cầu tất cả ID auto increment, nên hai cột này dùng `INT AUTO_INCREMENT`.

## DB credentials config

Sao chép `src/main/resources/db.properties.example` thành `src/main/resources/db.properties`, rồi nhập tài khoản MySQL cục bộ. File thật đã được `.gitignore`.

Có thể dùng biến môi trường thay thế:

```powershell
$env:DB_URL='jdbc:mysql://localhost:3306/web24162095?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true&useSSL=false'
$env:DB_USERNAME='root'
$env:DB_PASSWORD='YOUR_PASSWORD'
```

## SMTP config

Sao chép `src/main/resources/mail.properties.example` thành `src/main/resources/mail.properties`. Với Gmail, bật xác minh hai bước và dùng App Password, không dùng mật khẩu Gmail thường. File thật đã được `.gitignore`.

Luồng đăng ký tạo OTP ngẫu nhiên 6 chữ số, gửi SMTP, giữ pending registration trong Session và hết hạn sau 5 phút. Tài khoản chỉ được insert sau khi OTP hợp lệ; OTP không in ra browser. Gửi email thật qua Gmail SMTP đã được kiểm tra thành công bằng cấu hình cục bộ trong `mail.properties` (file này không được đưa vào Git).

## Build

```powershell
cd D:\JavaWeb\Project\24162095\WebNenThom
mvn clean package
```

Kết quả: `target/24162095_made.war`. Lần kiểm thử cuối: BUILD SUCCESS, 9 test, 0 failure/error.

## Deploy Tomcat

Tomcat phù hợp: 10.1.x. Đặt ba biến `DB_*` như trên, rồi:

```powershell
Copy-Item target\24162095_made.war D:\JavaWeb\apache-tomcat-10.1.44\webapps\
D:\JavaWeb\apache-tomcat-10.1.44\bin\startup.bat
```

Mở `http://localhost:8080/24162095_made/home`.

## Test accounts

Tất cả dùng mật khẩu mẫu `Phuc@123` (BCrypt trong database):

| Username | Role / Seller | Redirect sau login |
|---|---|---|
| `admin` | ADMIN | `/admin/home` |
| `user` | USER, không Seller | `/home` |
| `seller1` | USER, Seller 1 | `/seller/home` |
| `seller2` | USER, Seller 2 | `/seller/home` |

Không dùng các mật khẩu mẫu này cho hệ thống thật.

## Routes

- Public: `/home`, `/products`, `/product/detail?id=1`
- Auth: `/register`, `/verify-otp`, `/login`, `/logout`
- User Cart: `/cart`, POST `/cart/add`, `/cart/update`, `/cart/remove`, `/cart/clear`
- User COD: GET/POST `/checkout`, GET `/checkout/success`
- User Orders: `/orders`, `/orders?status=NEW`, `/order/detail?id=1`
- Seller: `/seller/home`
- Admin: `/admin/home`, `/admin/users`, `/admin/user/add`, `/admin/user/edit?id=`, `/admin/user/delete`
- Admin Category: `/admin/categories`, `/admin/category/add`, `/admin/category/edit?id=`, `/admin/category/delete`

## User Shopping Features

### Cart

- Mỗi User có tối đa một giỏ đang mua với `status=CART` trong luồng ứng dụng.
- Add mới hoặc cộng dồn đúng một row theo unique `(cartId, productId)`.
- Hỗ trợ tăng, giảm, nhập số lượng trực tiếp, xóa item và xóa toàn bộ.
- Backend bắt buộc `1 <= quantity <= Product.stock`; chặn product inactive/hết hàng.
- `unitPrice` lấy từ database khi thêm lần đầu và được giữ làm snapshot; không nhận giá từ browser.

### COD Checkout

- Form gồm người nhận, số điện thoại, địa chỉ, ghi chú; payment cố định `COD`.
- `CheckoutDAOImpl_24162095` dùng duy nhất một JDBC `Connection`, `setAutoCommit(false)` và `SELECT ... FOR UPDATE`.
- Stock được kiểm tra lại, total tính server-side từ CartItem snapshot, trừ stock có điều kiện và đổi `CART → NEW`.
- Bất kỳ item nào lỗi đều rollback toàn bộ; CartItem được giữ làm chi tiết đơn.
- Sau checkout, lần mở Cart tiếp theo tự tạo một Cart `status=CART` mới.

### Order History

- `/orders` chỉ lấy order của User hiện tại và luôn loại `CART`.
- Filter được whitelist bằng `OrderStatus_24162095`; giá trị lạ như `HACKED` fallback về tất cả.
- `/order/detail` query theo đồng thời `cartId` và `userId`, nên User khác nhận 404.

| Database code | Nhãn giao diện |
|---|---|
| `NEW` | Đơn hàng mới |
| `CONFIRMED` | Đã xác nhận |
| `PREPARING` | Chuẩn bị hàng |
| `IN_TRANSIT` | Vận chuyển |
| `OUT_FOR_DELIVERY` | Giao hàng |
| `DELIVERED` | Đã giao |
| `CANCELLED` | Đơn hàng hủy |
| `RETURNED` | Đơn hàng hoàn |

### Demo status trong MySQL Workbench

1. Login tài khoản `user`, mở `/orders`, ghi lại mã đơn.
2. Mở `database/order_status_demo_24162095.sql`, đặt `@order_id` bằng mã đơn.
3. Chạy **một** lệnh UPDATE trạng thái.
4. Refresh `/orders`, kiểm tra badge và filter tương ứng.
5. Lặp lại cho các status còn lại. Thay đổi `CANCELLED/RETURNED` thủ công chỉ để demo, không tự cộng lại stock.

Hướng dẫn trình diễn đầy đủ nằm trong `SHOPPING_DEMO_24162095.md`.

### Milestone commits

| Phase | Commit | Timestamp | Push |
|---|---|---|---|
| Cart | `0e21bc1a40e8ea8ddb638d7d11efb33e2101997b` | `2026-10-01T14:03:56+07:00` | `origin/main` thành công |
| COD | `1250e2cd775cee43e6cadead8e14907160ddc82f` | `2026-10-01T14:10:43+07:00` | `origin/main` thành công |
| Order History | `089bf03b46737dc55b80d8ea98d3759947977bfa` | `2026-10-01T14:19:31+07:00` | `origin/main` thành công |

## Question mapping

- Câu 1: `database/`, `connection/`, `dao/`, `service/`, controllers, filters, `WEB-INF/sitemesh3.xml`, hai decorators.
- Câu 2: `RegisterController_24162095`, `VerifyOtpController_24162095`, `LoginController_24162095`, `LogoutController_24162095`, `MailUtil_24162095`, auth JSP.
- Câu 3: `ProductDAOImpl_24162095.findAllOrderedBySeller`, `ProductServiceImpl_24162095.getProductsGroupedBySeller`, `ProductListController_24162095`, `products.jsp`.
- Câu 4: `ProductDAOImpl_24162095.getById`, `ProductDetailController_24162095`, `product-detail.jsp`.
- Câu 5: User/Category DAO, Service, admin controllers và JSP list/form; DAO dùng `LIMIT ? OFFSET ?`.

## Verified results

- MySQL: 7 tables, 7 FK, 9 sample users, 3 sellers, 10 products.
- Home/products/detail: HTTP 200; 3 seller groups; 10 products; detail 404 đúng khi ID không tồn tại.
- SiteMesh: web/admin decorator hoạt động; footer đủ họ tên/MSSV/đề; guest không thấy menu admin.
- Authorization: guest redirect login, seller vào admin nhận 403.
- Login redirects: ADMIN/User/Seller đúng; logout về login.
- User và Category CRUD: create/update/delete đã test HTTP + kiểm tra lại database.
- Pagination: page 2 đã test cho cả User và Category.
- Register validation: duplicate username/email đã test.
- OTP random/expiry: test tự động pass; Gmail SMTP thật đã gửi OTP thành công.
- Cart runtime: guest redirect login; add trùng cộng dồn; tăng/giảm/update/xóa; quantity 999 bị chặn và dữ liệu không đổi.
- COD rollback: cố ý hạ stock dưới quantity; Cart vẫn `CART`, các Product khác không bị trừ.
- COD success: order `NEW`, payment `COD`, total đúng, CartItem giữ nguyên, stock giảm đúng, Cart mới tạo sau đó.
- History: đủ 8 trạng thái/filter; `HACKED` fallback all; User khác truy cập order nhận 404.
- Maven: 9 tests, 0 failure, 0 error; `mvn clean test` và `mvn clean package` đều BUILD SUCCESS.

## Known limitations

- Gmail App Password chỉ được lưu trong `mail.properties` đã bị `.gitignore`; không chụp hoặc đưa file này vào bài nộp.
- Project dùng trường ảnh dạng đường dẫn/URL ổn định thay vì upload file; sample data có PNG theo từng nhóm sản phẩm và SVG dự phòng.
- Thay đổi trạng thái `CANCELLED`/`RETURNED` thủ công trong Workbench phục vụ demo không hoàn stock tự động.
- Project chưa có CSRF token; phù hợp phạm vi bài Servlet/JSP chạy cục bộ, không nên triển khai Internet công cộng khi chưa bổ sung lớp bảo vệ này.

## GitHub repository

Thư mục `WebNenThom` là bản source sạch để đưa lên GitHub. Repository không chứa báo cáo, archive nộp bài, `target`, runtime Tomcat hoặc file cấu hình có mật khẩu. Hai file `*.properties.example` được giữ lại làm mẫu cấu hình.
