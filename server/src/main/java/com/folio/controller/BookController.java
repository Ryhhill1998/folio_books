package com.folio.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.folio.dao.BookDao;
import com.folio.dao.OrderLineDao;
import com.folio.model.Book;
import com.folio.model.OrderLine;

@RestController //should be sent back as JSON
public class BookController {
    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Autowired
    OrderLineDao orderLineDao;

    @GetMapping("/get-books")
    public ResponseEntity<List<Book>> getBooks() {
        try {
            List<Book> books = bookDao.getAllBooks(); 
            return ResponseEntity.ok(books); //gives an ok status (HTTPS 200)
        } catch (Exception e) {
            // Handle the exception, e.g., log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/get-basket")
    public ResponseEntity<Map<String, Object>> getBasket(@RequestParam int customerId){

        Map<String, Object> response = new HashMap<>();

        List<Map<String, Object>> books = new ArrayList<>();
        List<OrderLine> basketOrderLines = orderLineDao.getOrderLinesInBasket(customerId);

        if (basketOrderLines == null) { // if customer has no books in basket
            response.put("books", null);
            return ResponseEntity.ok(response); // return customer info with empty basket
        }

        for (OrderLine orderLine : basketOrderLines) {
                    Map<String, Object> bookInfo = new HashMap<>();
                    bookInfo.put("bookId", orderLine.getBookId());
                    bookInfo.put("quantity", orderLine.getQuantity());
                    books.add(bookInfo);
                }

        response.put("books", books);
   
        return ResponseEntity.ok(response);
    }

    }