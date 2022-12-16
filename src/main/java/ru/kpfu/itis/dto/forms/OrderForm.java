package ru.kpfu.itis.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderForm {
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryHome;
    private String deliveryFlat;
}
