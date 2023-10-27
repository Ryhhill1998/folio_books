DROP DATABASE IF EXISTS ecommerce_db;
CREATE DATABASE ecommerce_db;
USE ecommerce_db;
CREATE TABLE book(
	id varchar(255),
    title varchar(255),
    author varchar(255),
    genre varchar(255),
    image_src varchar(255),
	stars float,
	price decimal(6, 2),
	stock_quantity int,
    PRIMARY KEY (id)
);

CREATE TABLE customer(
	id int auto_increment,
	fname varchar(255),
    sname varchar(255),
    email_address varchar(255) UNIQUE,
    mobile_num varchar(15),
    fline_address varchar(255),
    sline_address varchar(255),
    city varchar(255),
    post_code varchar(11),
    password_ varchar(50),
    card_num varchar(20),
    cvv varchar(3),
	PRIMARY KEY (id)
);

CREATE TABLE customer_order(
	id int auto_increment,
    customer_id int,
    status_ varchar(55),
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES customer(id)
);

CREATE TABLE order_line(
	id int auto_increment,
    order_id int,
    book_id varchar(255),
    quantity int,
    price_per_book decimal(6, 2),
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (order_id) REFERENCES customer_order(id)
);

