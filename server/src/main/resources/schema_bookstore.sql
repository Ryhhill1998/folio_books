DROP DATABASE IF EXISTS ecommerceDb;
CREATE DATABASE ecommerceDb;
USE ecommerceDb;

CREATE TABLE book(
	id varchar(255),
    title varchar(255),
    author varchar(255),
    genre varchar(255),
    imageSrc varchar(255),
	stars float,
	price decimal(6, 2),
	stockQuantity int,
    PRIMARY KEY (id)
);

CREATE TABLE customer(
	id int auto_increment,
	forename varchar(255),
    surname varchar(255),
    email varchar(255) UNIQUE,
    password_ varchar(50),
	PRIMARY KEY (id)
);

CREATE TABLE customerOrder(
	id int auto_increment,
    customerId int,
    status_ varchar(55),
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES customer(id)
);

CREATE TABLE orderLine(
	id int auto_increment,
    orderId int,
    bookId varchar(255),
    quantity int,
    pricePerBook decimal(6, 2),
    PRIMARY KEY (id),
    FOREIGN KEY (bookId) REFERENCES book(id),
    FOREIGN KEY (orderId) REFERENCES customerOrder(id)
);

