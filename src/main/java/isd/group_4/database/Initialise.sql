DROP TABLE USERS;
CREATE TABLE USERS (
    userID INTEGER PRIMARY KEY AUTOINCREMENT,
    password varchar(30);
    firstName varchar(40);
    lastName varchar(40);
    email varchar(100);
    phone varchar(10);
    streetNumber varchar(4);
    streetName varchar(30);
    suburb varchar(32);
    postcode varchar(4);
);