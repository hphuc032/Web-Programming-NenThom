USE web24162095;

INSERT INTO UserRoles(roleId,roleName) VALUES (1,'ADMIN'),(2,'USER');
INSERT INTO Seller(sellerId,sellername,images,status) VALUES
(1,'Phúc Technology','assets/images/category.svg',1),
(2,'Sài Gòn Home','assets/images/category.svg',1),
(3,'Campus Store','assets/images/category.svg',1);
INSERT INTO Category(categoryId,categoryName,images,status) VALUES
(1,'Điện thoại','assets/images/categories/phone.png',1),(2,'Laptop','assets/images/categories/laptop.png',1),
(3,'Gia dụng','assets/images/categories/home-appliance.png',1),(4,'Phụ kiện','assets/images/categories/tech-accessory.png',1),
(5,'Sách công nghệ','assets/images/categories/technology-book.png',1),(6,'Thời trang','assets/images/category.svg',1);

-- Mật khẩu cho tất cả tài khoản mẫu: Phuc@123
INSERT INTO Users(username,email,fullname,password,images,phone,status,code,roleId,sellerId) VALUES
('admin','admin@example.com','Nguyễn Hoàng Phúc','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000001',1,NULL,1,NULL),
('user','user@example.com','Người dùng Demo','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000002',1,NULL,2,NULL),
('seller1','seller1@example.com','Chủ Phúc Technology','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000003',1,NULL,2,1),
('seller2','seller2@example.com','Chủ Sài Gòn Home','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000004',1,NULL,2,2),
('seller3','seller3@example.com','Chủ Campus Store','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000005',1,NULL,2,3),
('demo6','demo6@example.com','Tài khoản phân trang 6','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000006',1,NULL,2,NULL),
('demo7','demo7@example.com','Tài khoản phân trang 7','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000007',1,NULL,2,NULL),
('demo8','demo8@example.com','Tài khoản phân trang 8','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000008',1,NULL,2,NULL),
('demo9','demo9@example.com','Tài khoản phân trang 9','$2a$12$G4t1riFrSpRfOq7pcvnQveDipcCl4uGyl3VIv/SU5EzCNbg14abOy','assets/images/category.svg','0901000009',1,NULL,2,NULL);

INSERT INTO Product(productName,productCode,categoryId,description,price,amount,stock,images,wishlist,status,createDate,sellerId) VALUES
('Điện thoại Alpha','PHONE-A01',1,'Điện thoại màn hình OLED, phù hợp học tập và công việc.',12990000,25,25,'assets/images/categories/phone.png',12,1,NOW(),1),
('Laptop JavaBook','LAP-J17',2,'Laptop cấu hình tốt cho Java 17, Maven và phát triển web.',21990000,12,12,'assets/images/categories/laptop.png',20,1,NOW(),1),
('Chuột không dây','ACC-M01',4,'Chuột không dây gọn nhẹ, pin bền.',390000,80,80,'assets/images/categories/tech-accessory.png',9,1,NOW(),1),
('Nồi chiên Air 6L','HOME-A6',3,'Nồi chiên không dầu dung tích 6 lít.',2490000,18,18,'assets/images/categories/home-appliance.png',17,1,NOW(),2),
('Máy lọc không khí','HOME-P2',3,'Máy lọc không khí cho phòng 30 mét vuông.',3990000,9,9,'assets/images/categories/home-appliance.png',6,1,NOW(),2),
('Tai nghe Bluetooth','ACC-BT9',4,'Tai nghe chống ồn chủ động, thời lượng pin 30 giờ.',1790000,31,31,'assets/images/categories/tech-accessory.png',15,1,NOW(),2),
('Giáo trình Servlet JSP','BOOK-SJ',5,'Tài liệu thực hành Servlet, JSP, JSTL và JDBC.',185000,55,55,'assets/images/categories/technology-book.png',22,1,NOW(),3),
('Balo Laptop 15 inch','FASH-B15',6,'Balo chống sốc, chống thấm nhẹ.',650000,40,40,'assets/images/product.svg',8,1,NOW(),3),
('Bàn phím cơ Campus','ACC-K87',4,'Bàn phím cơ 87 phím dành cho sinh viên lập trình.',1090000,23,23,'assets/images/categories/tech-accessory.png',14,1,NOW(),3),
('Sổ tay Web Developer','BOOK-WEB',5,'Sổ tay ghi chú bài tập lập trình web.',79000,100,100,'assets/images/categories/technology-book.png',3,1,NOW(),3);

INSERT INTO Cart(userId,buyDate,status) VALUES (2,NOW(),'CART');
INSERT INTO CartItem(quantity,unitPrice,productId,cartId) VALUES (1,390000,3,1),(2,79000,10,1);
