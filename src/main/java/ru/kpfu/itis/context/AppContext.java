package ru.kpfu.itis.context;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.kpfu.itis.dao.CartRepository;
import ru.kpfu.itis.dao.OrderRepository;
import ru.kpfu.itis.dao.ProductRepository;
import ru.kpfu.itis.dao.UserRepository;
import ru.kpfu.itis.dao.impl.CartRepositoryImpl;
import ru.kpfu.itis.dao.impl.OrderRepositoryImpl;
import ru.kpfu.itis.dao.impl.ProductRepositoryImpl;
import ru.kpfu.itis.dao.impl.UserRepositoryImpl;
import ru.kpfu.itis.exceptions.DbConnectionException;
import ru.kpfu.itis.exceptions.DbDriverException;
import ru.kpfu.itis.jdbc_connection.ConnectionProvider;
import ru.kpfu.itis.services.*;
import ru.kpfu.itis.services.impl.*;
import ru.kpfu.itis.services.validation.Validator;

import java.sql.Connection;

@WebListener
public class AppContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        try {
            Connection connection = ConnectionProvider.getInstance().getConnection();


            UserRepository userRepository = new UserRepositoryImpl(connection);
            servletContext.setAttribute("userRepository", userRepository);

            ProductRepository productRepository = new ProductRepositoryImpl(connection);
            servletContext.setAttribute("productRepository", productRepository);

            CartRepository cartRepository = new CartRepositoryImpl(connection);
            servletContext.setAttribute("cartRepository", cartRepository);

            OrderRepository orderRepository = new OrderRepositoryImpl(connection);
            servletContext.setAttribute("orderRepository", orderRepository);


            HashService hashService = new HashServiceImpl();
            servletContext.setAttribute("hashService", hashService);

            Validator validator = new ValidatorImpl(userRepository);
            servletContext.setAttribute("validator", validator);


            UserMapper userMapper = new UserMapperImpl(hashService);
            servletContext.setAttribute("userMapper", userMapper);

            ProductMapper productMapper = new ProductMapperImpl();
            servletContext.setAttribute("productMapper", productMapper);

            OrderMapper orderMapper = new OrderMapperImpl();
            servletContext.setAttribute("orderMapper", orderMapper);


            UserService userService = new UserServiceImpl(userRepository, userMapper, validator, hashService);
            servletContext.setAttribute("userService", userService);

            ProductService productService = new ProductServiceImpl(productRepository, productMapper);
            servletContext.setAttribute("productService", productService);

            CartService cartService = new CartServiceImpl(cartRepository, productRepository, productMapper);
            servletContext.setAttribute("cartService", cartService);

            OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper);
            servletContext.setAttribute("orderService", orderService);

        } catch (DbDriverException | DbConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
