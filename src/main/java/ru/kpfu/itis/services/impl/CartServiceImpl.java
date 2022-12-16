package ru.kpfu.itis.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.dao.CartRepository;
import ru.kpfu.itis.dao.ProductRepository;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.exceptions.NotFoundException;
import ru.kpfu.itis.models.Product;
import ru.kpfu.itis.services.CartService;
import ru.kpfu.itis.services.ProductMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void addToCart(Long cartId, Long productId) {
        Optional<Product> optionalProduct = productRepository.getById(productId);

        if (!optionalProduct.isPresent()) {
            throw new NotFoundException("Product with " + productId + " doesn't exist");
        }

        Product product = optionalProduct.get();

        if (!isInCart(cartId, productId)) {
            cartRepository.addProductToCart(cartId, productId, product.getPrice());
        }

    }

    @Override
    public Boolean isInCart(Long cartId, Long productId) {
        return cartRepository.isInCart(cartId, productId);
    }

    @Override
    public List<ProductDto> getAllProductsInCart(Long cartId) {
        return cartRepository.getAllProductsInCart(cartId)
                .stream()
                .map((productMapper::toProductDtoFromProduct))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductFromCart(Long cartId, Long productId) {
        cartRepository.deleteProductFromCart(cartId, productId);
    }

    @Override
    public void increaseProductAmount(Long cartId, Long productId) {
        cartRepository.increaseProductAmount(cartId, productId);
    }

    @Override
    public void decreaseProductAmount(Long cartId, Long productId) {
        cartRepository.decreaseProductAmount(cartId, productId);
    }

    @Override
    public Boolean isProductAmountEqualsOne(Long cartId, Long productId) {
        return cartRepository.isProductAmountEqualsOne(cartId, productId);
    }

    @Override
    public Long getTotalOrderPrice(Long cartId) {
        return cartRepository.getTotalOrderPrice(cartId);
    }

    @Override
    public Long getTotalOrderAmount(Long cartId) {
        return cartRepository.getTotalOrderAmount(cartId);
    }

    @Override
    public void clearCart(Long cartId) {
        cartRepository.deleteAllProductsInCart(cartId);
    }

    @Override
    public Long getProductAmountInCart(Long cartId, Long productId) {
        return cartRepository.getProductAmountInCart(cartId, productId);
    }
}
