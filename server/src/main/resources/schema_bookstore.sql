DROP DATABASE ecommerce_db;
CREATE DATABASE ecommerce_db;
USE ecommerce_db;

CREATE TABLE book(
	id int auto_increment,
    author_fname varchar(45),
    author_sname varchar(45),
    title varchar(255),
    genre varchar(255), 
    release_year int,
    age_rating int,
    customer_rating float,
    stock_quantity int,
    price decimal(6, 2),
    PRIMARY KEY (id)
);

CREATE TABLE customer(
	id int,
	fname varchar(255),
    sname varchar(255),
    email_address varchar(255),
    mobile_num int,
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
	id int,
    customer_id int,
    date_ date,
    status_ varchar(55),
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES customer(id)
);

CREATE TABLE order_line(
	id int,
    order_id int,
    book_id int,
    quantity int,
    price_per_book decimal(6, 2),
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (order_id) REFERENCES customer_order(id)
);


