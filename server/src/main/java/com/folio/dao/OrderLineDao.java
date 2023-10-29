package com.folio.dao;

import com.folio.dao.CustomerOrderDao;
import com.folio.model.CustomerOrder;
import com.folio.model.OrderLine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderLineDao {

    private JdbcTemplate jdbcTemplate;

    public OrderLineDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public CustomerOrderDao customerOrderDao;

    public OrderLine addOrderLine(OrderLine orderLine) {
        String sql = "INSERT INTO orderLine(orderId, bookId, quantity, pricePerBook) VALUES(?,?,?,?)";
        jdbcTemplate.update(
                sql,
                orderLine.getOrderId(),
                orderLine.getBookId(),
                orderLine.getQuantity(),
                orderLine.getPricePerBook()
        );

        // Fetch the generated ID and set it in the OrderLine object
        int generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        orderLine.setId(generatedId);

        return orderLine;
    }

    public OrderLine getOrderLineById(int id) {
        String sql = "SELECT * FROM orderLine WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new OrderLineMapper(), id);
    }

    public List<OrderLine> getOrderLinesByOrderId(int orderId) {
        String sql = "SELECT * FROM orderLine WHERE orderId = ?";
        return jdbcTemplate.query(sql, new OrderLineMapper(), orderId);
    }

    public List<OrderLine> getOrderLinesInBasket(int customerId) {
        try {
            CustomerOrder basketOrder = customerOrderDao.getBasketCustomerOrdersByCustomerId(customerId);
            List<OrderLine> basketOrderLines = getOrderLinesByOrderId(basketOrder.getId());
            return basketOrderLines;
        } catch (Exception e) {
            return null;
        }
        
    }

    public void updateOrderLine(OrderLine orderLine) {
        String sql = "UPDATE orderLine SET orderId = ?, bookId = ?, quantity = ?, pricePerBook = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                orderLine.getOrderId(),
                orderLine.getBookId(),
                orderLine.getQuantity(),
                orderLine.getPricePerBook(),
                orderLine.getId()
        );
    }

    public void deleteOrderLine(int id) {
        String sql = "DELETE FROM order_line WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class OrderLineMapper implements RowMapper<OrderLine> {
        @Override
        public OrderLine mapRow(ResultSet resultSet, int i) throws SQLException {
            OrderLine orderLine = new OrderLine();
            orderLine.setId(resultSet.getInt("id"));
            orderLine.setOrderId(resultSet.getInt("orderId"));
            orderLine.setBookId(resultSet.getString("bookId"));
            orderLine.setQuantity(resultSet.getInt("quantity"));
            orderLine.setPricePerBook(resultSet.getBigDecimal("pricePerBook"));
            return orderLine;
        }
    }
}
