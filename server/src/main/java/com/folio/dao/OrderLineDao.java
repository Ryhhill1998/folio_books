package com.folio.dao;

import com.folio.dao.CustomerOrderDao;
import com.folio.model.CustomerOrder;
import com.folio.model.OrderLine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    public CustomerOrderDao customerOrderDao;

    public OrderLine addOrderLine(OrderLine orderLine) {
        String sql = "INSERT INTO orderLines(order_id, book_id, quantity, price_per_book) VALUES(?,?,?,?)";
        jdbcTemplate.update(
                sql,
                orderLine.getOrder_id(),
                orderLine.getBook_id(),
                orderLine.getQuantity(),
                orderLine.getPrice_per_book()
        );

        // Fetch the generated ID and set it in the OrderLine object
        int generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        orderLine.setId(generatedId);

        return orderLine;
    }

    public OrderLine getOrderLineById(int id) {
        String sql = "SELECT * FROM order_line WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new OrderLineMapper(), id);
    }

    public List<OrderLine> getOrderLinesByOrderId(int orderId) {
        String sql = "SELECT * FROM order_line WHERE order_id = ?";
        return jdbcTemplate.query(sql, new OrderLineMapper(), orderId);
    }

    public List<OrderLine> getBasket(int customerId) {
        CustomerOrder basketOrder = customerOrderDao.getBasketCustomerOrdersByCustomerId(customerId);
        List<OrderLine> basketOrderLines = getOrderLinesByOrderId(basketOrder.getId());
        return basketOrderLines;
    }

    public void updateOrderLine(OrderLine orderLine) {
        String sql = "UPDATE order_line SET order_id = ?, book_id = ?, quantity = ?, price_per_book = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                orderLine.getOrder_id(),
                orderLine.getBook_id(),
                orderLine.getQuantity(),
                orderLine.getPrice_per_book(),
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
            orderLine.setOrder_id(resultSet.getInt("order_id"));
            orderLine.setBook_id(resultSet.getString("book_id"));
            orderLine.setQuantity(resultSet.getInt("quantity"));
            orderLine.setPrice_per_book(resultSet.getBigDecimal("price_per_book"));
            return orderLine;
        }
    }
}
