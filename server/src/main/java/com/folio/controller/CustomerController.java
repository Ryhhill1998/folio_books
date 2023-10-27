package com.folio.controller;

import com.folio.dao.CustomerDao;
import com.folio.dao.CustomerOrderDao;
import com.folio.dao.OrderLineDao;
import com.folio.model.Customer;
import com.folio.model.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController // should be sent back as JSON
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerOrderDao customerOrderDao;

    @Autowired
    OrderLineDao orderLineDao;

    @GetMapping("/get")
    public ResponseEntity<String> getTestJson() {
        String jsonResponse = "{\"message\": \"This is a test JSON response.\"}";

        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, Object>> signInCustomer(@RequestParam String email,
            @RequestParam String password) {
        try {
            Customer authenticatedCustomer = customerDao.authenticateCustomer(email, password);

            if (authenticatedCustomer != null) {
                List<OrderLine> basketOrderLines = orderLineDao.getBasket(authenticatedCustomer.getId());
                List<Map<String, Object>> books = new ArrayList<>();

                for (OrderLine orderLine : basketOrderLines) {
                    Map<String, Object> bookInfo = new HashMap<>();
                    bookInfo.put("book_id", orderLine.getBook_id());
                    bookInfo.put("quantity", orderLine.getQuantity());
                    books.add(bookInfo);
                }

                Map<String, Object> response = new HashMap<>();
                response.put("id", authenticatedCustomer.getId());
                response.put("fname", authenticatedCustomer.getFname());
                response.put("books", books);

                return ResponseEntity.ok(response);

            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Sign-in failed"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Sign-in failed"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerMap customer) {
        try {
            customerDao.createCustomer(customer);
            return ResponseEntity.ok("Registration successful");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
}
