package ru.kpfu.itis.services;

import ru.kpfu.itis.dto.ProductDto;

import java.util.List;

public interface CartService {
    void addToCart(Long cartId, Long productId);
    Boolean isInCart(Long cartId, Long productId);
    List<ProductDto> getAllProductsInCart(Long cartId);
    void deleteProductFromCart(Long cartId, Long productId);
    void increaseProductAmount(Long cartId, Long productId);
    void decreaseProductAmount(Long cartId, Long productId);
    Boolean isProductAmountEqualsOne(Long cartId, Long productId);
    Long getTotalOrderPrice(Long cartId);
    Long getTotalOrderAmount(Long cartId);
    void clearCart(Long cartId);
    Long getProductAmountInCart(Long cartId, Long productId);
}
