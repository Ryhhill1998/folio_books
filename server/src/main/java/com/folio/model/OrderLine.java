package com.folio.model;

import java.math.BigDecimal;

public class OrderLine {
    int id;
    int orderId;
    String bookId;
    int quantity;
    BigDecimal pricePerBook;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBook_id(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerBook() {
        return pricePerBook;
    }

    public void setPricePerBook(BigDecimal pricePerBook) {
        this.pricePerBook = pricePerBook;
    }
}
