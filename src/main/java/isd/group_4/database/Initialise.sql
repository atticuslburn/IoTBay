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
(1, 1300, 1600);
-- CUSTOMER INFORMATION MANAGEMENT table (Esha)
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