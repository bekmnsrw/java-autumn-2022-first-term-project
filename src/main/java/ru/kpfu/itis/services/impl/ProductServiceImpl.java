package ru.kpfu.itis.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.dao.ProductRepository;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.dto.forms.ProductForm;
import ru.kpfu.itis.exceptions.NotFoundException;
import ru.kpfu.itis.models.Product;
import ru.kpfu.itis.services.ProductMapper;
import ru.kpfu.itis.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {
        return productRepository.getAll()
                .stream()
                .map((productMapper::toProductDtoFromProduct))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<Product> optionalProduct = productRepository.getById(id);

        if (!optionalProduct.isPresent()) {
            throw new NotFoundException("");
        }

        Product product = optionalProduct.get();

        return productMapper.toProductDtoFromProduct(product);
    }

    @Override
    public ProductDto saveProduct(ProductForm productForm) {
        Product product = productMapper.toProductFromProductForm(productForm);
        Product p = productRepository.save(product);
        return productMapper.toProductDtoFromProduct(p);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<ProductDto> sortProductsByPriceIncreasing() {
        return productRepository.sortByPriceIncreasing()
                .stream()
                .map((productMapper::toProductDtoFromProduct))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> sortProductsByPriceDecreasing() {
        return productRepository.sortByPriceDecreasing()
                .stream()
                .map((productMapper::toProductDtoFromProduct))
                .collect(Collectors.toList());
    }

    @Override
    public void update(ProductDto productDto) {
        productRepository.updateProduct(productMapper.toProductFromProductDto(productDto));
    }

    @Override
    public List<ProductDto> filterFigures() {
        return productRepository.filterFigures()
                .stream()
                .map((productMapper::toProductDtoFromProduct))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> filterManga() {
        return productRepository.filterManga()
                .stream()
                .map((productMapper::toProductDtoFromProduct))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> filterSnacks() {
        return productRepository.filterSnacks()
                .stream()
                .map((productMapper::toProductDtoFromProduct))
                .collect(Collectors.toList());
    }
}
