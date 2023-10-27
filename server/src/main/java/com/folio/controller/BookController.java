package com.folio.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.folio.dao.BookDao;
import com.folio.model.Book;

@RestController //should be sent back as JSON
public class BookController {
    private final BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/get-books")
    public ResponseEntity<List<Book>> getBooks() {
        try {
            List<Book> books = bookDao.getAllBooks(); 
            return ResponseEntity.ok(books); //gives an ok status (HTTPS 200)
        } catch (SQLException e) {
            // Handle the exception, e.g., log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

