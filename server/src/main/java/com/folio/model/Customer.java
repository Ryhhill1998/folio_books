package com.folio.model;

public class Customer {
    int id;
    String fname;
    String sname;
    String email_address;
    String fline_address;
    String sline_address;
    String city;
    String post_code;
    String password;
    String card_num;
    String cvv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getFline_address() {
        return fline_address;
    }

    public void setFline_address(String fline_address) {
        this.fline_address = fline_address;
    }

    public String getSline_address() {
        return sline_address;
    }

    public void setSline_address(String sline_address) {
        this.sline_address = sline_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
