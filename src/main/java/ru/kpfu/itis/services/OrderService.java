package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.OrderDto;
import ru.kpfu.itis.models.Order;

import java.util.List;

public interface OrderService {
    Order makeOrder(Order order);
    void saveOrderProductConnectivity(Long orderId, Long productId, Long amount);
    List<OrderDto> getAllOrders(Long userId);
    List<Long[]> getAllProductsInOrder(Long orderId);
    Boolean isProductInOrder(Long productId);
}
