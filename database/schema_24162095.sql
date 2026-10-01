CREATE DATABASE IF NOT EXISTS web24162095 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE web24162095;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS CartItem;
DROP TABLE IF EXISTS Cart;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Seller;
DROP TABLE IF EXISTS UserRoles;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE UserRoles (
    roleId INT PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE Seller (
    sellerId INT PRIMARY KEY AUTO_INCREMENT,
    sellername VARCHAR(120) NOT NULL,
    images VARCHAR(500),
    status BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB;

CREATE TABLE Category (
    categoryId INT PRIMARY KEY AUTO_INCREMENT,
    categoryName VARCHAR(120) NOT NULL,
    images VARCHAR(500),
    status BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB;

CREATE TABLE Users (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(60) NOT NULL UNIQUE,
    email VARCHAR(160) NOT NULL UNIQUE,
    fullname VARCHAR(160) NOT NULL,
    password VARCHAR(100) NOT NULL,
    images VARCHAR(500),
    phone VARCHAR(30),
    status BOOLEAN NOT NULL DEFAULT FALSE,
    code VARCHAR(100),
    roleId INT NOT NULL,
    sellerId INT NULL,
    CONSTRAINT fk_users_role FOREIGN KEY (roleId) REFERENCES UserRoles(roleId),
    CONSTRAINT fk_users_seller FOREIGN KEY (sellerId) REFERENCES Seller(sellerId) ON DELETE SET NULL
) ENGINE=InnoDB;

CREATE TABLE Product (
    productId INT PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(180) NOT NULL,
    productCode VARCHAR(60) NOT NULL UNIQUE,
    categoryId INT NOT NULL,
    description TEXT,
    price DECIMAL(15,2) NOT NULL CHECK (price >= 0),
    amount INT NOT NULL DEFAULT 0 CHECK (amount >= 0),
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    images VARCHAR(500),
    wishlist INT NOT NULL DEFAULT 0,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    createDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    sellerId INT NOT NULL,
    CONSTRAINT fk_product_category FOREIGN KEY (categoryId) REFERENCES Category(categoryId),
    CONSTRAINT fk_product_seller FOREIGN KEY (sellerId) REFERENCES Seller(sellerId)
) ENGINE=InnoDB;

CREATE TABLE Cart (
    cartId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    buyDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(30) NOT NULL DEFAULT 'CART',
    receiverName VARCHAR(160),
    receiverPhone VARCHAR(30),
    shippingAddress VARCHAR(500),
    paymentMethod VARCHAR(30),
    totalAmount DECIMAL(15,2),
    note VARCHAR(500),
    CONSTRAINT fk_cart_user FOREIGN KEY (userId) REFERENCES Users(userId)
) ENGINE=InnoDB;

CREATE TABLE CartItem (
    cartItemId INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL CHECK (quantity > 0),
    unitPrice DECIMAL(15,2) NOT NULL CHECK (unitPrice >= 0),
    productId INT NOT NULL,
    cartId INT NOT NULL,
    CONSTRAINT fk_cartitem_product FOREIGN KEY (productId) REFERENCES Product(productId),
    CONSTRAINT fk_cartitem_cart FOREIGN KEY (cartId) REFERENCES Cart(cartId) ON DELETE CASCADE,
    CONSTRAINT uq_cart_product UNIQUE (cartId, productId)
) ENGINE=InnoDB;
