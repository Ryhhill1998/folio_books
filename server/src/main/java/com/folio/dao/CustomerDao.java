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
        // Fetch the max customer ID and increment by 1 to create the new ID
        int lastCustomerId = getLastCustomerId();
        int newCustomerId = lastCustomerId + 1;

        String sql = "INSERT INTO customer (id, fname, sname, email_address, fline_address, sline_address, city, post_code, password_, card_num, cvv) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, newCustomerId, customer.getFname(), customer.getSname(),
                customer.getEmail_address(), customer.getFline_address(), customer.getSline_address(),
                customer.getCity(), customer.getPost_code(), customer.getPassword(), customer.getCard_num(),
                customer.getCvv());
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

    public Customer getCustomerByEmail(String email_address) {
        String sql = "SELECT * FROM customer WHERE email_address = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), email_address);
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET fname = ?, sname = ?, email_address = ?, fline_address = ?, " +
                "sline_address = ?, city = ?, post_code = ?, password_ = ?, card_num = ?, cvv = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, customer.getFname(), customer.getSname(), customer.getEmail_address(),
                customer.getFline_address(), customer.getSline_address(), customer.getCity(),
                customer.getPost_code(), customer.getPassword(), customer.getCard_num(), customer.getCvv(),
                customer.getId());
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Customer authenticateCustomer(String email_address, String password) {
        String sql = "SELECT * FROM customer WHERE email_address = ? AND password_ = ?";
        
        try {
            return jdbcTemplate.queryForObject(sql, new CustomerMapper(), email_address, password);
        } catch (EmptyResultDataAccessException ex) {
            return null; // Customer not found or incorrect credentials
        }
    }

    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setFname(resultSet.getString("fname"));
            customer.setSname(resultSet.getString("sname"));
            customer.setEmail_address(resultSet.getString("email_address"));
            customer.setFline_address(resultSet.getString("fline_address"));
            customer.setSline_address(resultSet.getString("sline_address"));
            customer.setCity(resultSet.getString("city"));
            customer.setPost_code(resultSet.getString("post_code"));
            customer.setPassword(resultSet.getString("password_"));
            customer.setCard_num(resultSet.getString("card_num"));
            customer.setCvv(resultSet.getString("cvv"));
            return customer;
        }
    }

    
}
