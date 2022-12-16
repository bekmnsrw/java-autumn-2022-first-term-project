package ru.kpfu.itis.dao;

import ru.kpfu.itis.models.Product;

import java.util.List;

public interface CartRepository {
    void addProductToCart(Long cartId, Long productId, Long price);
    Boolean isInCart(Long cartId, Long productId);
    List<Product> getAllProductsInCart(Long cartId);
    void deleteProductFromCart(Long cartId, Long productId);
    void increaseProductAmount(Long cartId, Long productId);
    void decreaseProductAmount(Long cartId, Long productId);
    boolean isProductAmountEqualsOne(Long cartId, Long productId);
    Long getTotalOrderPrice(Long cartId);
    Long getTotalOrderAmount(Long cartId);
    void deleteAllProductsInCart(Long cartId);
    Long getProductAmountInCart(Long cartId, Long productId);
}
