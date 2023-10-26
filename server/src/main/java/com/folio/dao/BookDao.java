package com.folio.dao;

import com.folio.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Connection connection;

    public BookDao(Connection connection) {
        this.connection = connection;
    }

    public void createBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (id, title, author, genre, image_src, stars, price, stock_quantity) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getGenre());
            statement.setString(5,book.getImage_src());
            statement.setFloat(6,Float.parseFloat(book.getStars()));
            statement.setBigDecimal(7, book.getPrice());
            statement.setInt(8, book.getStock_quantity());
            statement.executeUpdate();

        }
    }

    public Book getBookById(String id) throws SQLException {
        String sql = "SELECT * FROM book WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBook(resultSet);
                }
                return null; // Book not found
            }
        }
    }

    public List<Book> getBooksByAuthor(String author) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE author = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(mapResultSetToBook(resultSet));
                }
            }
        }

        return books;
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                books.add(mapResultSetToBook(resultSet));
            }
        }

        return books;
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, genre = ?, image_src = ?, stars = ?, price = ?, stock_quantity = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setString(4, book.getImage_src());
            statement.setFloat(5, Float.parseFloat(book.getStars()));
            statement.setBigDecimal(6, book.getPrice());
            statement.setInt(7, book.getStock_quantity());
            statement.setString(8, book.getId());
            statement.executeUpdate();
        }
    }

    public void deleteBook(String id) throws SQLException {
        String sql = "DELETE FROM book WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }

    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getString("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setGenre(resultSet.getString("genre"));
        book.setImage_src(resultSet.getString("image_src"));
        book.setStars(String.valueOf(resultSet.getFloat("stars")));
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setStock_quantity(resultSet.getInt("stock_quantity"));
        return book;
    }
}
