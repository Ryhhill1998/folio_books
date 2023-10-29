package com.folio.controller;

import com.folio.dao.BookDao;
import com.folio.dao.CustomerOrderDao;
import com.folio.dao.OrderLineDao;
import com.folio.model.Book;
import com.folio.model.CustomerOrder;
import com.folio.model.OrderLine;

import java.util.Map;
import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    CustomerOrderDao customerOrderDao;

    @Autowired
    OrderLineDao orderLineDao;

    @Autowired
    BookDao bookDao;

    @PostMapping("/add-to-basket")
    public ResponseEntity<Map<String, Object>> addToBasket(@RequestParam int customerId, @RequestParam String bookId) {
        
        try {
            // check if customer already has order in basket
            CustomerOrder customerOrder = customerOrderDao.getBasketCustomerOrdersByCustomerId(customerId); 
            
            if (customerOrder == null){
                // create new customerOrder if no order created
                customerOrder = new CustomerOrder();
                customerOrder.setCustomer_id(customerId);
                customerOrder.setStatus_("Basket");
                customerOrderDao.createCustomerOrder(customerOrder);
            }
            
            // check if book already in basket
            List<OrderLine> orderLinesInBasket = orderLineDao.getOrderLinesInBasket(customerId);
            
            boolean exists = false;

            for (OrderLine orderLine : orderLinesInBasket ) {
                if (orderLine.getBookId().equals(bookId)) { // if orderLine already exists for book
                    exists = true;
                    int quantity = orderLine.getQuantity();
                    orderLine.setQuantity(++quantity);
                    orderLine.setPricePerBook(bookDao.getBookById(bookId).getPrice());
                    orderLineDao.updateOrderLine(orderLine);
                    break;
                } 
            }

            if (!exists) { // create new orderLine if not already in orderLine
                OrderLine orderLine = new OrderLine();
                orderLine.setOrderId(customerOrder.getId());
                orderLine.setBookId(bookId);
                orderLine.setQuantity(customerId);
                orderLine.setPricePerBook(bookDao.getBookById(bookId).getPrice());
                orderLineDao.addOrderLine(orderLine);
            } 

            return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(Collections.singletonMap("message", "success"));
        } catch (Exception e) {
            return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(Collections.singletonMap("message", "failure"));
        }

    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(@RequestParam int customerId) {

        try {
            CustomerOrder customerOrder = customerOrderDao.getBasketCustomerOrdersByCustomerId(customerId);
            customerOrder.setStatus_("Completed");
            customerOrderDao.updateCustomerOrder(customerOrder);

            return ResponseEntity
                            .status(HttpStatus.OK)
                            .body(Collections.singletonMap("message", "success"));

        } catch (Exception e) {
            return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(Collections.singletonMap("message", "failure"));
        }


    }

}
