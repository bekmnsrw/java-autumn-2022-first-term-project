package ru.kpfu.itis.dao.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.itis.dao.CartRepository;
import ru.kpfu.itis.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final Connection connection;

    private final static String SQL_ADD_PRODUCT_TO_CART = "INSERT INTO cart (user_id, product_id, price) VALUES (?, ?, ?)";
    private final static String SQL_IS_IN_CART = "SELECT * FROM cart WHERE user_id = ? AND product_id = ?";
    private final static String SQL_GET_ALL_PRODUCTS_IN_CART = "SELECT id, name, description, p.price as price, category FROM cart INNER JOIN product p ON p.id = cart.product_id WHERE user_id = ? ORDER BY product_id";
    private final static String SQL_DELETE_PRODUCT_FROM_CART = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
    private final static String SQL_INCREASE_PRODUCT_AMOUNT = "UPDATE cart SET amount = amount + 1 WHERE user_id = ? AND product_id = ?";
    private final static String SQL_DECREASE_PRODUCT_AMOUNT = "UPDATE cart SET amount = amount - 1 WHERE user_id = ? AND product_id = ?";
    private final static String SQL_IS_PRODUCT_AMOUNT_MORE_THAN_ZERO = "SELECT * FROM cart WHERE user_id = ? AND product_id = ? AND amount = 1";
    private final static String SQL_GET_TOTAL_ORDER_PRICE = "SELECT SUM(price * amount) AS total_price FROM cart WHERE user_id = ?";
    private final static String SQL_GET_TOTAL_ORDER_AMOUNT = "SELECT SUM(amount) AS total_amount FROM cart WHERE user_id = ?";
    private final static String SQL_DELETE_ALL_PRODUCTS_IN_CART = "DELETE FROM cart WHERE user_id = ?";
    private final static String SQL_GET_PRODUCT_AMOUNT_IN_CART = "SELECT amount FROM cart WHERE user_id = ? AND product_id = ?";


    private static final Function<ResultSet, Product> mapper = row -> {
        try {
            return Product.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .description(row.getString("description"))
                    .price(row.getLong("price"))
                    .category(row.getString("category"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public void addProductToCart(Long cartId, Long productId, Long price) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT_TO_CART);
            preparedStatement.setLong(1, cartId);
            preparedStatement.setLong(2, productId);
            preparedStatement.setLong(3, price);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Boolean isInCart(Long cartId, Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_IN_CART);
            preparedStatement.setLong(1, cartId);
            preparedStatement.setLong(2, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Product> getAllProductsInCart(Long cartId) {
        List<Product> productsInCart = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS_IN_CART);
            preparedStatement.setLong(1, cartId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productsInCart.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return productsInCart;
    }

    @Override
    public void deleteProductFromCart(Long cartId, Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT_FROM_CART);
            preparedStatement.setLong(1, cartId);
            preparedStatement.setLong(2, productId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void increaseProductAmount(Long cartId, Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INCREASE_PRODUCT_AMOUNT);
            preparedStatement.setLong(1, cartId);
            preparedStatement.setLong(2, productId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void decreaseProductAmount(Long cartId, Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DECREASE_PRODUCT_AMOUNT);
            preparedStatement.setLong(1, cartId);
            preparedStatement.setLong(2, productId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean isProductAmountEqualsOne(Long cartId, Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_PRODUCT_AMOUNT_MORE_THAN_ZERO);
            preparedStatement.setLong(1, cartId);
            preparedStatement.setLong(2, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Long getTotalOrderPrice(Long cartId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_ORDER_PRICE);
            preparedStatement.setLong(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getLong("total_price");

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Long getTotalOrderAmount(Long cartId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TOTAL_ORDER_AMOUNT);
            preparedStatement.setLong(1, cartId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getLong("total_amount");

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void deleteAllProductsInCart(Long cartId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ALL_PRODUCTS_IN_CART);
            preparedStatement.setLong(1, cartId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Long getProductAmountInCart(Long cartId, Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PRODUCT_AMOUNT_IN_CART);
            preparedStatement.setLong(1, cartId);
            preparedStatement.setLong(2, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getLong("amount");

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
