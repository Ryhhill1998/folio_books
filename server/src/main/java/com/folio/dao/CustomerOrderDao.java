package com.folio.dao;

import com.folio.model.CustomerOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDao {

    private Connection connection;

    public CustomerOrderDao(Connection connection) {
        this.connection = connection;
    }

    public void createCustomerOrder(CustomerOrder customerOrder) throws SQLException {
        // Fetch the max customer ID and increment by 1 to create the new ID
        int lastCustomerOrderId = getLastCustomerOrderId();
        int newCustomerOrderId = lastCustomerOrderId + 1;

        // Prepare the SQL INSERT statement
        String sql = "INSERT INTO customer_order (id, customer_id, date_, status) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newCustomerOrderId);
            statement.setInt(2, customerOrder.getCustomer_id());
            statement.setDate(3, java.sql.Date.valueOf(customerOrder.getDate_()));
            statement.setString(4, customerOrder.getStatus_());
            statement.executeUpdate();
        }
    }

    private int getLastCustomerOrderId() throws SQLException {
        String sql = "SELECT MAX(id) FROM customer_order";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            // If the table is empty, return 0 or any other default value as needed
            return 0;
        }
    }


    public CustomerOrder getCustomerOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM customer_order WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomerOrder(resultSet);
                }
                return null; // CustomerOrder not found
            }
        }
    }

    public List<CustomerOrder> getCustomerOrdersByCustomerId(int customerId) throws SQLException {
        List<CustomerOrder> customerOrders = new ArrayList<>();
        String sql = "SELECT * FROM customer_order WHERE customer_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customerOrders.add(mapResultSetToCustomerOrder(resultSet));
                }
            }
        }

        return customerOrders;
    }

    public void updateCustomerOrder(CustomerOrder customerOrder) throws SQLException {
        String sql = "UPDATE customer_order SET customer_id = ?, date_ = ?, status = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerOrder.getCustomer_id());
            statement.setDate(2, java.sql.Date.valueOf(customerOrder.getDate_()));
            statement.setString(3, customerOrder.getStatus_());
            statement.setInt(4, customerOrder.getId());
            statement.executeUpdate();
        }
    }

    public void deleteCustomerOrder(int id) throws SQLException {
        String sql = "DELETE FROM customer_order WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private CustomerOrder mapResultSetToCustomerOrder(ResultSet resultSet) throws SQLException {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setId(resultSet.getInt("id"));
        customerOrder.setCustomer_id(resultSet.getInt("customer_id"));
        customerOrder.setDate_(resultSet.getDate("date_").toLocalDate());
        customerOrder.setStatus_(resultSet.getString("status"));
        return customerOrder;
    }
}
