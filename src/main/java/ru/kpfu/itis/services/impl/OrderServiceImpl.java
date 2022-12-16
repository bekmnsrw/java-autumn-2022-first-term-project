package ru.kpfu.itis.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.dao.OrderRepository;
import ru.kpfu.itis.dto.OrderDto;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.services.OrderMapper;
import ru.kpfu.itis.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order makeOrder(Order order) {
        return orderRepository.saveOrder(order);
    }

    @Override
    public void saveOrderProductConnectivity(Long orderId, Long productId, Long amount) {
        orderRepository.saveOrderProductConnectivity(orderId, productId, amount);
    }

    @Override
    public List<OrderDto> getAllOrders(Long userId) {
        return orderRepository.getAllOrders(userId)
                .stream()
                .map((orderMapper::toOrderDtoFromOrder))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long[]> getAllProductsInOrder(Long orderId) {
        return orderRepository.getAllProductsInOrder(orderId);
    }

    @Override
    public Boolean isProductInOrder(Long productId) {
        return orderRepository.isProductInOrder(productId);
    }
}
