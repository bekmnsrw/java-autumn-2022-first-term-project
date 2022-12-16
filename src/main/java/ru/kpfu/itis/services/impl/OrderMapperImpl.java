package ru.kpfu.itis.services.impl;

import ru.kpfu.itis.dto.OrderDto;
import ru.kpfu.itis.dto.forms.OrderForm;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.services.OrderMapper;

public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order toOrderFromOrderForm(OrderForm orderForm, String date, Long price, Long userId) {
        return Order.builder()
                .date(date)
                .price(price)
                .deliveryCity(orderForm.getDeliveryCity())
                .deliveryStreet(orderForm.getDeliveryStreet())
                .deliveryHome(orderForm.getDeliveryHome())
                .deliveryFlat(orderForm.getDeliveryFlat())
                .userId(userId)
                .build();
    }

    @Override
    public OrderDto toOrderDtoFromOrder(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .date(order.getDate())
                .price(order.getPrice())
                .deliveryCity(order.getDeliveryCity())
                .deliveryStreet(order.getDeliveryStreet())
                .deliveryHome(order.getDeliveryHome())
                .deliveryFlat(order.getDeliveryFlat())
                .build();
    }
}
