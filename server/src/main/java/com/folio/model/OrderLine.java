package com.folio.model;

import java.math.BigDecimal;

public class OrderLine {
    int id;
    int order_id;
    String book_id;
    int quantity;
    BigDecimal price_per_book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice_per_book() {
        return price_per_book;
    }

    public void setPrice_per_book(BigDecimal price_per_book) {
        this.price_per_book = price_per_book;
    }
}
