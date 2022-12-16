package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.OrderDto;
import ru.kpfu.itis.dto.forms.OrderForm;
import ru.kpfu.itis.models.Order;

public interface OrderMapper {
    Order toOrderFromOrderForm(OrderForm orderForm, String date, Long price, Long userId);
    OrderDto toOrderDtoFromOrder(Order order);
}
