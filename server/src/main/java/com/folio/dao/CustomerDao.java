package com.folio.dao;

import com.folio.model.Customer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDao {

    private JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createCustomer(Customer customer) {
        // Validate email format
        String forename = customer.getForename();
        String surname = customer.getSurname();
        String email = customer.getEmail();
        String password_ = customer.getPassword_();

        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Check for empty fields
        if (forename == null || forename.isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }

        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }

        // Fetch the max customer ID and increment by 1 to create the new ID
        int lastCustomerId;
        try {
            lastCustomerId = getLastCustomerId();
        } catch (EmptyResultDataAccessException e) {
            // Handle the case when there are no existing customers
            lastCustomerId = 0;
        }

        int newCustomerId = ++lastCustomerId;

        String sql = "INSERT INTO customer (id, forename, surname, email, password_)"
                +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, newCustomerId, forename, surname, email, password_);
    }

    private int getLastCustomerId() {
        String sql = "SELECT MAX(id) FROM customer";
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        if (maxId == null) {
            return 0;
        } else {
            return maxId;
        }
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), id);
    }

    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), email);
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET forename = ?, surname = ?, email = ?, " +
                "password_ = ? WHERE id = ?";
        jdbcTemplate.update(sql, customer.getForename(), customer.getSurname(), customer.getEmail(),
                customer.getPassword_(), customer.getId());
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Customer authenticateCustomer(String email, String password_) {
        String sql = "SELECT * FROM customer WHERE email = ? AND password_ = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new CustomerMapper(), email, password_);
        } catch (EmptyResultDataAccessException ex) {
            return null; // Customer not found or incorrect credentials
        }
    }

    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setForename(resultSet.getString("forename"));
            customer.setSurname(resultSet.getString("surname"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPassword_(resultSet.getString("password_"));
            return customer;
        }
    }
}
