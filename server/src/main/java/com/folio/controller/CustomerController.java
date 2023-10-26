package com.folio.controller;

import com.folio.dao.CustomerDao;
import com.folio.model.Customer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

}