package com.folio.dao;

import com.folio.model.OrderLine;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineDao {

    private Connection connection;

    public OrderLineDao(Connection connection) {
        this.connection = connection;
    }

    public OrderLine addOrderLine(OrderLine orderLine) throws SQLException {
        String sql = "INSERT INTO orderLines(order_id, book_id, quantity, price_per_book) VALUES(?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, orderLine.getOrder_id());
            statement.setString(2, orderLine.getBook_id());
            statement.setInt(3, orderLine.getQuantity());
            statement.setBigDecimal(4, orderLine.getPrice_per_book());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderLine.setId(generatedKeys.getInt(1));
                }
            }
        }

        return orderLine;
    }



    public OrderLine getOrderLineById(int id) throws SQLException {
        String sql = "SELECT * FROM order_line WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToOrderLine(resultSet);
                }
                return null; // OrderLine not found
            }
        }
    }

    public List<OrderLine> getOrderLinesByOrderId(int orderId) throws SQLException {
        List<OrderLine> orderLines = new ArrayList<>();
        String sql = "SELECT * FROM order_line WHERE order_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orderLines.add(mapResultSetToOrderLine(resultSet));
                }
            }
        }

        return orderLines;
    }

    public void updateOrderLine(OrderLine orderLine) throws SQLException {
        String sql = "UPDATE order_line SET order_id = ?, book_id = ?, quantity = ?, price_per_book = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderLine.getOrder_id());
            statement.setString(2, orderLine.getBook_id());
            statement.setInt(3, orderLine.getQuantity());
            statement.setBigDecimal(4, orderLine.getPrice_per_book());
            statement.setInt(5, orderLine.getId());
            statement.executeUpdate();
        }
    }

    public void deleteOrderLine(int id) throws SQLException {
        String sql = "DELETE FROM order_line WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private OrderLine mapResultSetToOrderLine(ResultSet resultSet) throws SQLException {
        OrderLine orderLine = new OrderLine();
        orderLine.setId(resultSet.getInt("id"));
        orderLine.setOrder_id(resultSet.getInt("order_id"));
        orderLine.setBook_id(resultSet.getString("book_id"));
        orderLine.setQuantity(resultSet.getInt("quantity"));
        orderLine.setPrice_per_book(resultSet.getBigDecimal("price_per_book"));
        return orderLine;
    }
}

