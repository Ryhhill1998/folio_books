package com.folio.dao;

import com.folio.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    public void createCustomer(Customer customer) throws SQLException {
        // Fetch the max customer ID and increment by 1 to create the new ID
        int lastCustomerId = getLastCustomerId();
        int newCustomerId = lastCustomerId + 1;

        String sql = "INSERT INTO customer (id, fname, sname, email_address, fline_address, sline_address, city, post_code, password, card_num, cvv) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newCustomerId);
            statement.setString(2, customer.getFname());
            statement.setString(3, customer.getSname());
            statement.setString(4, customer.getEmail_address());
            statement.setString(5, customer.getFline_address());
            statement.setString(6, customer.getSline_address());
            statement.setString(7, customer.getCity());
            statement.setString(8, customer.getPost_code());
            statement.setString(9, customer.getPassword());
            statement.setString(10, customer.getCard_num());
            statement.setString(11, customer.getCvv());
            statement.executeUpdate();
        }
    }

    private int getLastCustomerId() throws SQLException {
        String sql = "SELECT MAX(id) FROM customer";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            // If the table is empty, return 0 or any other default value as needed
            return 0;
        }
    }


    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
                return null; // Customer not found
            }
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                customers.add(mapResultSetToCustomer(resultSet));
            }
        }

        return customers;
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET fname = ?, sname = ?, email_address = ?, fline_address = ?, " +
                "sline_address = ?, city = ?, post_code = ?, password = ?, card_num = ?, cvv = ? " +
                "WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getFname());
            statement.setString(2, customer.getSname());
            statement.setString(3, customer.getEmail_address());
            statement.setString(4, customer.getFline_address());
            statement.setString(5, customer.getSline_address());
            statement.setString(6, customer.getCity());
            statement.setString(7, customer.getPost_code());
            statement.setString(8, customer.getPassword());
            statement.setString(9, customer.getCard_num());
            statement.setString(10, customer.getCvv());
            statement.setInt(11, customer.getId());
            statement.executeUpdate();
        }
    }

    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM customer WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setFname(resultSet.getString("fname"));
        customer.setSname(resultSet.getString("sname"));
        customer.setEmail_address(resultSet.getString("email_address"));
        customer.setFline_address(resultSet.getString("fline_address"));
        customer.setSline_address(resultSet.getString("sline_address"));
        customer.setCity(resultSet.getString("city"));
        customer.setPost_code(resultSet.getString("post_code"));
        customer.setPassword(resultSet.getString("password"));
        customer.setCard_num(resultSet.getString("card_num"));
        customer.setCvv(resultSet.getString("cvv"));
        return customer;
    }
}
