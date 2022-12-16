package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.dto.forms.ProductForm;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto saveProduct(ProductForm productForm);
    void delete(Long id);
    List<ProductDto> sortProductsByPriceIncreasing();
    List<ProductDto> sortProductsByPriceDecreasing();
    void update(ProductDto productDto);
    List<ProductDto> filterFigures();
    List<ProductDto> filterManga();
    List<ProductDto> filterSnacks();
}
