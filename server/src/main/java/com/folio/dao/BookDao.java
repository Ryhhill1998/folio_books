package com.folio.dao;

import com.folio.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createBook(Book book) {
        String sql = "INSERT INTO book (id, title, author, genre, imageSrc, stars, price, stockQuantity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(),
                book.getImageSrc(), book.getStars(), book.getPrice(), book.getStockQuantity());
    }

    public Book getBookById(String id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BookMapper(), id);
    }

    public List<Book> getBooksByAuthor(String author) {
        String sql = "SELECT * FROM book WHERE author = ?";
        return jdbcTemplate.query(sql, new BookMapper(), author);
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM book";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    public void updateBook(Book book) {
        String sql = "UPDATE book SET title = ?, author = ?, genre = ?, image_src = ?, stars = ?, price = ?, stockQuantity = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getGenre(), book.getImageSrc(),
                book.getStars(), book.getPrice(), book.getStockQuantity(), book.getId());
    }

    public void deleteBook(String id) {
        String sql = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();
            book.setId(resultSet.getString("id"));
            book.setTitle(resultSet.getString("title"));
            book.setAuthor(resultSet.getString("author"));
            book.setGenre(resultSet.getString("genre"));
            book.setImageSrc(resultSet.getString("imageSrc"));
            book.setStars(resultSet.getFloat("stars"));
            book.setPrice(resultSet.getBigDecimal("price"));
            book.setStockQuantity(resultSet.getInt("stockQuantity"));
            return book;
        }
    }
}
