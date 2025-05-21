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
    postcode varchar(4),
    role varchar(40)
);
INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode, role) VALUES
('abc', 'John@citizen','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000', 'customer');
INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode, role) VALUES
    ('abc', 'bruh@gmail','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000', 'admin');
INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode, role) VALUES
    ('abc', 'yoho@fake','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000', 'customer');

INSERT INTO USERS (password, email, firstName, lastName, phoneNumber, streetNumber, streetName, suburb, postcode, role) VALUES
    ('abc', 'jafdnjnfds@fzdnjfds','John', 'Citizen', '0123456789', '6', 'Tanglewood Drive', 'Ultimo', '0000', 'admin');
SELECT * FROM USERS;

-- USER PERMISSIONS – SHOULD NOT BE USED
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

('Apple iPhone 15', '6.1-inch OLED display, A16 Bionic chip, Dynamic Island feature', 10, 899.00),
('Apple iPhone 16', 'Next-gen A17 chip, improved thermal performance, 5G enhanced', 10, 1049.00),

('Apple iPhone 16 Pro', '6.7-inch display, periscope telephoto, 2TB storage option', 5, 1399.00),
('Apple iPhone 16 e', 'Slimmer model, energy-efficient, lightweight build', 5, 749.00),
('Apple AirPods Pro', 'Active Noise Cancellation, Adaptive Transparency, MagSafe charging', 20, 249.00),

('Google Nest Hub', '7-inch smart display with Google Assistant, home control and media', 12, 99.00),
('Amazon Echo Dot', 'Compact smart speaker with Alexa and improved bass', 15, 59.99),
('TP Link Kasa Smart Plug', 'Wi-Fi outlet with energy monitoring, voice control', 25, 24.99),
('Philips Hue Starter Kit', 'Includes smart bulbs and hub, supports voice and app control', 10, 179.00),
('LG InstaView Smart Fridge', 'Smart refrigerator with glass panel and Wi-Fi touch screen', 3, 2899.00);

-- ORDERS
DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS (
    orderID INTEGER PRIMARY KEY AUTOINCREMENT,
    userID INTEGER,
    orderDate DATETIME,
    CONSTRAINT userFK FOREIGN KEY (userID) REFERENCES USERS(userID)
);

INSERT INTO ORDERS (userID, orderDate, paymentID) VALUES (1, '2001-10-05 10:05:01', 1);
INSERT INTO ORDERS (userID, orderDate, paymentID) VALUES (1, '2002-12-05 10:05:01', 1);

-- ORDERITEM
DROP TABLE IF EXISTS ORDERITEM;
CREATE TABLE ORDERITEM (
    oiID INTEGER PRIMARY KEY AUTOINCREMENT,
    orderID INTEGER,
    itemID INTEGER,
    orderQuantity INTEGER,
    CONSTRAINT orderFK FOREIGN KEY (orderID) REFERENCES ORDERS(orderID),
    CONSTRAINT itemFK FOREIGN KEY (itemID) REFERENCES ITEMS(itemID)
);

INSERT INTO ORDERITEM (orderID, itemID, orderQuantity) VALUES (1, 1, 1);







--logs
drop table if exists user_access_log;
create table user_access_log (
    id integer primary key autoincrement,
    user_id integer not null,
    login_time datetime not null,
    logout_time datetime,
    constraint userfk foreign key (user_id) references USERS(userID)
);
insert into user_access_log (user_id, login_time, logout_time) values
(1, '1990-01-01 12:00:00.000', '1991-01-02 14:15:00.000'),
(1, '1991-01-01 12:00:00.000', '1992-01-02 14:15:00.000'),
(1, '1992-01-01 12:00:00.000', '1993-01-02 14:15:00.000'),
(1, '1993-01-01 12:00:00.000', '1994-01-02 14:15:00.000'),
(1, '1994-01-01 12:00:00.000', '1995-01-02 14:15:00.000'),
(1, '1995-01-01 12:00:00.000', '1996-01-02 14:15:00.000'),
(1, '1996-01-01 12:00:00.000', '1997-01-02 14:15:00.000'),
(1, '1997-01-01 12:00:00.000', '1998-01-02 14:15:00.000'),
(1, '1998-01-01 12:00:00.000', '1999-01-02 14:15:00.000'),
(1, '1999-01-01 12:00:00.000', '2000-01-02 14:15:00.000'),
(1, '2000-01-01 12:00:00.000', '2001-01-02 14:15:00.000'),
(1, '2001-01-01 12:00:00.000', '2002-01-02 14:15:00.000'),
(1, '2002-01-01 12:00:00.000', '2003-01-02 14:15:00.000'),
(1, '2003-01-01 12:00:00.000', '2004-01-02 14:15:00.000');

-- CUSTOMER INFORMATION MANAGEMENT table
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
                           id       INTEGER PRIMARY KEY AUTOINCREMENT,
                           name     VARCHAR(100)  NOT NULL,
                           email    VARCHAR(100)  UNIQUE NOT NULL,
                           type     VARCHAR(20)   NOT NULL,       -- 'individual' or 'company'
                           address  VARCHAR(255)  NOT NULL,
                           active   BOOLEAN       DEFAULT TRUE
);

INSERT INTO customers(name,email,type,address,active) VALUES
('Alice Smith','alice.smith@example.com','individual','12 Rose St, Sydney',TRUE),
('Bob Johnson','bob.johnson@example.com','individual','34 Maple Rd, Melbourne',TRUE),
('Carla Reyes','carla.reyes@example.com','individual','56 Pine Ave, Brisbane',TRUE),
('Delta Co.','contact@deltaco.com','company','78 Birch Blvd, Perth',TRUE),
('Echo Enterprises','info@echoent.com','company','90 Cedar Ln, Adelaide',TRUE),
('Franklin LLC','support@franklinllc.com','company','123 Elm St, Hobart',TRUE),
('Grace Lee','grace.lee@example.com','individual','45 Oak Dr, Darwin',TRUE),
('Hector Gomez','hector.gomez@example.com','individual','67 Cherry Pl, Canberra',TRUE),
('Ivy Innovations','hello@ivyinnov.com','company','89 Walnut Way, Newcastle',TRUE),
('Jones & Co.','sales@jonesco.com','company','101 Poplar Rd, Wollongong',TRUE),
('Kyle Nguyen','kyle.nguyen@example.com','individual','11 Palm Ct, Geelong',TRUE),
('Luna Ltd.','contact@lunaltd.com','company','22 Cypress St, Gold Coast',TRUE),
('Mia Patel','mia.patel@example.com','individual','33 Spruce Rd, Sunshine Coast',TRUE),
('Nova Corp.','info@novacorp.com','company','44 Redwood Ave, Townsville',TRUE),
('Olivia Brown','olivia.brown@example.com','individual','55 Fir Ln, Toowoomba',TRUE),
('PrimeTech','support@primetech.com','company','66 Aspen St, Ballarat',TRUE),
('Quinn Enterprises','hello@quinnent.com','company','77 Willow Way, Bendigo',TRUE),
('Ryan Scott','ryan.scott@example.com','individual','88 Sequoia Drive, Cairns',TRUE),
('Sigma Services','contact@sigmasvc.com','company','99 Linden Rd, Albury',TRUE),
('Tina Tran','tina.tran@example.com','individual','14 Eucalyptus St, Mackay',TRUE);



-- CARD Details

CREATE TABLE IF NOT EXISTS Cards (
     cardID INTEGER PRIMARY KEY AUTOINCREMENT,
     userID INTEGER,
     cardTypeID INTEGER,
     bankName TEXT,
     cardNumber TEXT,
     cardHolderName TEXT,
     cardExpiryDate TEXT,
     FOREIGN KEY (userID) REFERENCES USERS(userID),
     FOREIGN KEY (cardTypeID) REFERENCES CardType(cardTypeID)
);

--TEST Values
INSERT INTO Cards (userID, cardTypeID, bankName, cardNumber, cardHolderName, cardExpiryDate) VALUES
     (1, 1, 'ANZ', '4111111111111111', 'John Citizen', '2026-12'),      -- cardID 1
     (1, 1, 'CBA', '4111111111111111', 'John Citizen', '2026-12'),      -- cardID 2
     (2, 2, 'CBA', '5500000000000004', 'Bruh Admin', '2025-11'),        -- cardID 3
     (3, 3, 'Westpac', '340000000000009', 'Yoho Fake', '2027-05'),      -- cardID 4
     (4, 1, 'NAB', '4111111111111111', 'Jafdn Admin', '2028-08');       -- cardID 5



-- CARD TYPE
DROP TABLE IF EXISTS CardType;
CREATE TABLE CardType(
     cardTypeID INTEGER PRIMARY KEY AUTOINCREMENT,  --Primary Key
     cardType TEXT
);

-- Test Values
INSERT INTO CardType (cardType) VALUES
    ('Visa'),
    ('MasterCard'),
    ('American Express');


-- PAYMENTS
DROP TABLE IF EXISTS PAYMENTS;
CREATE TABLE PAYMENTS (
      paymentID INTEGER PRIMARY KEY AUTOINCREMENT,
      orderID INTEGER,
      userID INTEGER,
      cardID INTEGER,
      paymentStatus BOOLEAN,
      paymentAmount INTEGER,
      paymentDate TEXT,
      FOREIGN KEY (orderID) REFERENCES ORDERS(orderID),
      FOREIGN KEY (userID) REFERENCES USERS(userID),
      FOREIGN KEY (cardID) REFERENCES Cards(cardID)
    );


-- Inserting Payments Data

INSERT INTO PAYMENTS (orderID, userID, cardID, paymentStatus, paymentAmount, paymentDate) VALUES
      (1, 1, 1, 1, 799, '2024-06-01'),
      (1, 1, 2, 1, 799, '2024-09-01'),
      (2, 2, 3, 0, 999, '2024-06-02'),
      (3, 3, 4, 1, 1200, '2024-06-03'),
      (4, 4, 5, 1, 500, '2024-06-04');
