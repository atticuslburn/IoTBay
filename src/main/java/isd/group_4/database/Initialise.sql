-- USER TABLE
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
    userID INTEGER PRIMARY KEY AUTOINCREMENT,
    password varchar(30),
    email varchar(100),
    firstName varchar(40),
    lastName varchar(40),
    phoneNumber varchar(10),
    streetNumber varchar(4),
    streetName varchar(30),
    suburb varchar(32),
    postcode varchar(4)
);
INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode) VALUES
('abc', 'John@citizen','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000');
INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode) VALUES
    ('abc', 'bruh@gmail','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000');
INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode) VALUES
    ('abc', 'yoho@fake','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000');
SELECT * FROM USERS;

-- USER PERMISSIONS
DROP TABLE IF EXISTS USERPERMISSIONS;
CREATE TABLE USERPERMISSIONS (
    userID INTEGER PRIMARY KEY,
    isRegistered BOOLEAN,
    isAdmin BOOLEAN,
    isMerchant BOOLEAN,
    CONSTRAINT userFK FOREIGN KEY (userID) REFERENCES USERS(userID)
);

INSERT INTO USERPERMISSIONS (userID, isRegistered, isAdmin, isMerchant) VALUES (1, true, true, true);

SELECT COUNT(*) FROM USERPERMISSIONS WHERE (isAdmin = true OR isMerchant = true);


-- ITEM TABLE
DROP TABLE IF EXISTS ITEMS;

CREATE TABLE ITEMS (
                       itemID INTEGER PRIMARY KEY AUTOINCREMENT,
                       itemName TEXT NOT NULL,
                       itemDescription TEXT,
                       quantity INTEGER,
                       price REAL
);

INSERT INTO ITEMS (itemName, itemDescription, quantity, price) VALUES
('Apple iPhone 14', '6.1-inch display, A15 chip, dual-camera system', 10, 799.00),
('Apple iPhone 15', '6.1-inch display, A16 chip, dynamic island design', 10, 899.00),
('Apple iPhone 16', '6.1-inch display, A17 chip, improved battery life', 10, 999.00),
('Apple iPhone 14 Pro', '6.1-inch display, ProMotion, triple-camera system', 5, 1099.00),
('Apple iPhone 15 Pro', 'Titanium body, A17 Pro chip, USB-C port', 5, 1199.00),
('Apple iPhone 15 Pro', 'Titanium body, A17 Pro chip, USB-C port', 5, 1199.00),
('Apple iPhone 16 Pro', 'Latest Pro features, enhanced GPU, spatial video', 5, 1299.00),
('Apple iPhone 16e', 'Lighter', 5, 799.00),
('Apple AirPods Pro', 'Noise-canceling earbuds with spatial audio', 20, 249.00);


-- ORDER
DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS (
    orderID INTEGER PRIMARY KEY AUTOINCREMENT,
    itemID INTEGER,
    userID INTEGER,
    orderQuantity INTEGER,
    orderDate DATE,
    CONSTRAINT itemFK FOREIGN KEY (itemID) REFERENCES ITEMS(itemID),
    CONSTRAINT userFK FOREIGN KEY (userID) REFERENCES USERS(userID)
);