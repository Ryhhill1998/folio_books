use ecommerceDb;

INSERT INTO customer (forename, surname, email, password_) VALUES
('John', 'Doe', 'johndoe@email.com', 'password123'),
('Jane', 'Smith', 'janesmith@email.com', 'securepass'),
('Michael', 'Johnson', 'michael@email.com', 'mypass123'),
('Sarah', 'Brown', 'sarah@email.com', 'pass123word');

INSERT INTO customerOrder(customerId, status_) VALUES
(1, "Basket"),
(2, "Basket");

INSERT INTO orderLine(orderId, bookId, quantity, pricePerBook) VALUES
(1, "d36cec23-322e-43cd-9497-8c435cf735e3", 1, 14.99),
(1, "a8c0d959-3341-4657-a466-4290e164e761", 2, 11.99),
(1, "0383c29d-df78-4745-a873-09c7745b013a", 1, 4.99),
(2, "917deac5-a7d5-4255-a7b2-3ab27d6f8701", 4, 4.49);