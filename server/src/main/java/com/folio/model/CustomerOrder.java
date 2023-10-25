package com.folio.model;

import java.time.LocalDate;

public class CustomerOrder {
    int id;
    int customer_id;
    LocalDate date_;
    String status_;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDate getDate_() {
        return date_;
    }

    public void setDate_(LocalDate date_) {
        this.date_ = date_;
    }

    public String getStatus_() {
        return status_;
    }

    public void setStatus_(String status_) {
        this.status_ = status_;
    }
}
