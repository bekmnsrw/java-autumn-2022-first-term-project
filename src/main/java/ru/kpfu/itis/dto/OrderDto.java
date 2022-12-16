package ru.kpfu.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String date;
    private Long price;
    private String deliveryCity;
    private String deliveryStreet;
    private String deliveryHome;
    private String deliveryFlat;
}
