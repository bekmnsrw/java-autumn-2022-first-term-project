package ru.kpfu.itis.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.dto.forms.ProductForm;
import ru.kpfu.itis.models.Product;
import ru.kpfu.itis.services.ProductMapper;

@AllArgsConstructor
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDto toProductDtoFromProduct(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }

    @Override
    public Product toProductFromProductForm(ProductForm productForm) {
        return Product.builder()
                .name(productForm.getName())
                .description(productForm.getDescription())
                .price(productForm.getPrice())
                .category(productForm.getCategory())
                .build();
    }

    @Override
    public Product toProductFromProductDto(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .build();
    }
}
