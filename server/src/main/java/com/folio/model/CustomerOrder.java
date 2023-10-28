package com.folio.model;

public class CustomerOrder {
    int id;
    int customerId;
    String status_;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomer_id(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus_() {
        return status_;
    }

    public void setStatus_(String status_) {
        this.status_ = status_;
    }
}
