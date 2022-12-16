package ru.kpfu.itis.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductForm {
    private String name;
    private String description;
    private Long price;
    private String category;
}
