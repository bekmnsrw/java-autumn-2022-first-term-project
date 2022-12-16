package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.dto.forms.ProductForm;
import ru.kpfu.itis.models.Product;

public interface ProductMapper {
    ProductDto toProductDtoFromProduct(Product product);
    Product toProductFromProductForm(ProductForm productForm);

    Product toProductFromProductDto(ProductDto productDto);
}
