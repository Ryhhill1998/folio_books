package com.folio.controller;

import com.folio.dao.CustomerDao;
import com.folio.model.Customer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

@Controller //should be sent back as JSON
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signInCustomer(@RequestBody Customer customer) {
        // Authentication logic
        try {
            Customer authenticatedCustomer = customerDao.authenticateCustomer(customer.getEmail_address(), customer.getPassword());
            if (authenticatedCustomer != null) {
                return ResponseEntity.ok("Sign-in successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sign-in failed");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sign-in failed");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Validated @RequestBody Customer customer) { //Maps data to customer object and adds validation NotNull etc
        // Registration logic
        try { //try catch block to track errors
            customerDao.createCustomer(customer); //Create Customer method call
            return ResponseEntity.ok("Registration successful"); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }


}

    