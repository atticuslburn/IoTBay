DROP TABLE USERS;
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