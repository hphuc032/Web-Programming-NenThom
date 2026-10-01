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

Database `web24162095` có đúng 7 bảng: `UserRoles`, `Users`, `Seller`, `Category`, `Product`, `Cart`, `CartItem`; 7 foreign key và mọi primary key ID đều `AUTO_INCREMENT`.

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

Kết quả: `target/24162095_made.war`. Lần kiểm thử cuối: BUILD SUCCESS, 2 test, 0 failure/error.

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
- Seller: `/seller/home`
- Admin: `/admin/home`, `/admin/users`, `/admin/user/add`, `/admin/user/edit?id=`, `/admin/user/delete`
- Admin Category: `/admin/categories`, `/admin/category/add`, `/admin/category/edit?id=`, `/admin/category/delete`

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

## Known limitations

- Gmail App Password chỉ được lưu trong `mail.properties` đã bị `.gitignore`; không chụp hoặc đưa file này vào bài nộp.
- Project dùng trường ảnh dạng đường dẫn/URL ổn định thay vì upload file; sample data có PNG theo từng nhóm sản phẩm và SVG dự phòng.
- Các chức năng Cart/CartItem được tạo schema và sample theo đề nhưng không có UI vì 5 câu không yêu cầu cart workflow.

## GitHub repository

Thư mục `WebNenThom` là bản source sạch để đưa lên GitHub. Repository không chứa báo cáo, archive nộp bài, `target`, runtime Tomcat hoặc file cấu hình có mật khẩu. Hai file `*.properties.example` được giữ lại làm mẫu cấu hình.
