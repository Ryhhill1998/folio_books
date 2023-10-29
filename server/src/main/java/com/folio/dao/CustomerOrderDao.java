package com.folio.dao;

import com.folio.model.CustomerOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerOrderDao {

    private JdbcTemplate jdbcTemplate;

    public CustomerOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createCustomerOrder(CustomerOrder customerOrder) {
        // Fetch the max customer order ID and increment by 1 to create the new ID
        int lastCustomerOrderId = getLastCustomerOrderId();
        int newCustomerOrderId = lastCustomerOrderId + 1;

        // Prepare the SQL INSERT statement
        String sql = "INSERT INTO customerOrder (id, customerId, status_) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, newCustomerOrderId, customerOrder.getCustomerId(), customerOrder.getStatus_());
    }

    private int getLastCustomerOrderId() {
        String sql = "SELECT MAX(id) FROM customerOrder";
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        if (maxId == null) {
            return 0;
        } else {
            return maxId;
        }
    }

    public CustomerOrder getCustomerOrderById(int id) {
        String sql = "SELECT * FROM customerOrder WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerOrderMapper(), id);
    }

    public List<CustomerOrder> getCustomerOrdersByCustomerId(int customerId) {
        String sql = "SELECT * FROM customerOrder WHERE customerId = ?";
        return jdbcTemplate.query(sql, new CustomerOrderMapper(), customerId);
    }

    public CustomerOrder getBasketCustomerOrdersByCustomerId(int customerId) {
        String sql = "SELECT * FROM customerOrder WHERE customerId = ? AND status_ = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerOrderMapper(), customerId, "Basket");
    }

    public void updateCustomerOrder(CustomerOrder customerOrder) {
        String sql = "UPDATE customerOrder SET customerId = ?, status_ = ? WHERE id = ?";
        jdbcTemplate.update(sql, customerOrder.getCustomerId(), customerOrder.getStatus_(), customerOrder.getId());
    }

    public void deleteCustomerOrder(int id) {
        String sql = "DELETE FROM customerOrder WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class CustomerOrderMapper implements RowMapper<CustomerOrder> {
        @Override
        public CustomerOrder mapRow(ResultSet resultSet, int i) throws SQLException {
            CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setId(resultSet.getInt("id"));
            customerOrder.setCustomer_id(resultSet.getInt("customerId"));
            customerOrder.setStatus_(resultSet.getString("status_"));
            return customerOrder;
        }
    }
}
