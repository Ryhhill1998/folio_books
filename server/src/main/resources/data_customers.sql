use ecommerce_db;

INSERT INTO customer (fname, sname, email_address, mobile_num, fline_address, sline_address, city, post_code, password_, card_num, cvv) VALUES
('John', 'Doe', 'johndoe@email.com', '555-123-4567', '123 Main Street', 'Apt 4B', 'New York', '10001', 'password123', '1234 5678 9012 3456', '123'),
('Jane', 'Smith', 'janesmith@email.com', '555-987-6543', '456 Elm Street', '', 'Los Angeles', '90002', 'securepass', '5678 9012 3456 7890', '456'),
('Michael', 'Johnson', 'michael@email.com', '555-555-5555', '789 Oak Avenue', 'Suite 10', 'Chicago', '60603', 'mypass123', '9876 5432 1098 7654', '789'),
('Sarah', 'Brown', 'sarah@email.com', '555-321-6549', '567 Pine Lane', '', 'Houston', '77001', 'pass123word', '2345 6789 0123 4567', '234');

INSERT INTO customer_order(customer_id, status_) VALUES
(1, "Basket"),
(2, "Basket");

INSERT INTO order_line(order_id, book_id, quantity, price_per_book) VALUES

(1, "d36cec23-322e-43cd-9497-8c435cf735e3", 1, 14.99),

(1, "a8c0d959-3341-4657-a466-4290e164e761", 2, 11.99),

(1, "0383c29d-df78-4745-a873-09c7745b013a", 1, 4.99),

(2, "917deac5-a7d5-4255-a7b2-3ab27d6f8701", 4, 4.49);