package ru.kpfu.itis.dao.impl;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import ru.kpfu.itis.dao.ProductRepository;
import ru.kpfu.itis.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final Connection connection;

    private final static String SQL_GET_ALL_PRODUCTS = "SELECT * FROM product";
    private final static String SQL_SORT_BY_PRICE_INCREASING = "SELECT * FROM product ORDER BY price";
    private final static String SQL_SORT_BY_PRICE_DECREASING = "SELECT * FROM product ORDER BY price DESC";
    private final static String SQL_GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    private final static String SQL_SAVE = "INSERT INTO product (name, description, price, category) VALUES (?, ?, ?, ?)";
    private final static String SQL_DELETE = "DELETE FROM product WHERE id = ?";
    private final static String SQL_UPDATE = "UPDATE product SET name = ?, description = ?, price = ?, category = ? WHERE id = ?";
    private final static String SQL_FILTER_FIGURES = "SELECT * FROM product WHERE category = 'Figures'";
    private final static String SQL_FILTER_MANGA = "SELECT * FROM product WHERE category = 'Manga'";
    private final static String SQL_FILTER_SNACKS = "SELECT * FROM product WHERE category = 'Snacks'";

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
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }

    @Override
    public Optional<Product> getById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PRODUCT_BY_ID);
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = mapper.apply(resultSet);
                    return Optional.of(product);
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Product save(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setLong(3, product.getPrice());
            preparedStatement.setString(4, product.getCategory());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Can't save product");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Can't obtain generated key");
            }

            return mapper.apply(generatedKeys);

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Long productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, productId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<Product> sortByPriceIncreasing() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SORT_BY_PRICE_INCREASING);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }

    @Override
    public List<Product> sortByPriceDecreasing() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SORT_BY_PRICE_DECREASING);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }

    @Override
    public void updateProduct(Product product) {
        Product product1 = getById(product.getId()).orElse(null);

        if (product1 != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setLong(3, product.getPrice());
                preparedStatement.setString(4, product.getCategory());
                preparedStatement.setLong(5, product.getId());

                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            save(product);
        }
    }

    @Override
    public List<Product> filterFigures() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FILTER_FIGURES);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }

    @Override
    public List<Product> filterManga() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FILTER_MANGA);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }

    @Override
    public List<Product> filterSnacks() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FILTER_SNACKS);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(mapper.apply(resultSet));
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }
}
