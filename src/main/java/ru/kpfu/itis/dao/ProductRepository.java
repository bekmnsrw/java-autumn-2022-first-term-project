package ru.kpfu.itis.dao;

import ru.kpfu.itis.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<Product> getById(Long id);
    Product save(Product product);
    void delete(Long productId);
    List<Product> sortByPriceIncreasing();
    List<Product> sortByPriceDecreasing();
    void updateProduct(Product product);
    List<Product> filterFigures();
    List<Product> filterManga();
    List<Product> filterSnacks();
}
