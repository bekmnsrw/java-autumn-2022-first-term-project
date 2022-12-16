package ru.kpfu.itis.dao;

import ru.kpfu.itis.models.Order;

import java.util.List;

public interface OrderRepository {
    Order saveOrder(Order order);
    void saveOrderProductConnectivity(Long orderId, Long productId, Long amount);
    List<Order> getAllOrders(Long userId);
    List<Long[]> getAllProductsInOrder(Long orderId);
    Boolean isProductInOrder(Long productId);
}
