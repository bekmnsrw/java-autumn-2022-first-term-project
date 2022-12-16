package ru.kpfu.itis.dao.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.dao.OrderRepository;
import ru.kpfu.itis.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final Connection connection;

    private final static String SQL_SET_ORDER = "INSERT INTO orders (date, price, delivery_city, delivery_street, delivery_home, delivery_flat, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_SAVE_ORDER_PRODUCT_CONNECTIVITY = "INSERT INTO order_product (order_id, product_id, amount) VALUES (?, ?, ?)";
    private final static String SQL_GET_ALL_ORDERS = "SELECT * FROM orders WHERE user_id = ? ORDER BY date DESC";
    private final static String SQL_GET_ALL_PRODUCTS_IN_ORDER = "SELECT * FROM order_product WHERE order_id = ?";
    private final static String SQL_IS_IN_ORDER = "SELECT * FROM order_product WHERE product_id = ?";

    private final static Function<ResultSet, Order> mapper = row -> {
        try {
            return Order.builder()
                    .id(row.getLong("id"))
                    .date(row.getString("date"))
                    .price(row.getLong("price"))
                    .deliveryCity(row.getString("delivery_city"))
                    .deliveryStreet(row.getString("delivery_street"))
                    .deliveryHome(row.getString("delivery_home"))
                    .deliveryFlat(row.getString("delivery_flat"))
                    .userId(row.getLong("user_id"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public Order saveOrder(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getDate());
            preparedStatement.setLong(2, order.getPrice());
            preparedStatement.setString(3, order.getDeliveryCity());
            preparedStatement.setString(4, order.getDeliveryStreet());
            preparedStatement.setString(5, order.getDeliveryHome());
            preparedStatement.setString(6, order.getDeliveryFlat());
            preparedStatement.setLong(7, order.getUserId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Can't save order");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Can't obtain generated key");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return order;
    }

    @Override
    public void saveOrderProductConnectivity(Long orderId, Long productId, Long amount) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ORDER_PRODUCT_CONNECTIVITY);
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            preparedStatement.setLong(3, amount);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Order> getAllOrders(Long userId) {
        List<Order> orders = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_ORDERS);
            preparedStatement.setLong(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return orders;
    }

    @Override
    public List<Long[]> getAllProductsInOrder(Long orderId) {
        List<Long[]> productIds = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS_IN_ORDER);
            preparedStatement.setLong(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long[] result = new Long[2];
                    result[0] = resultSet.getLong("product_id");
                    result[1] = resultSet.getLong("amount");
                    productIds.add(result);
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return productIds;
    }

    @Override
    public Boolean isProductInOrder(Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_IN_ORDER);
            preparedStatement.setLong(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
