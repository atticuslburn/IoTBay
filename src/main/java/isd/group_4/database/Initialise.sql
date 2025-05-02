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
INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode) VALUES ('abc', 'John@citizen','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000');

SELECT * FROM USERS;

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
                                                                   ('iPhone 14', 'Apple iPhone 14 with A15 chip', 10, 999.99),
                                                                   ('iPhone 15 Mini', 'Smaller version with improved camera', 5, 1099.00),
                                                                   ('Smart Plug', 'WiFi smart plug for home automation', 20, 29.95);