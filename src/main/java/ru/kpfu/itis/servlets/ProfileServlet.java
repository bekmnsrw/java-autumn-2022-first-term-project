package ru.kpfu.itis.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.dto.OrderDto;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.services.OrderService;
import ru.kpfu.itis.services.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        orderService = (OrderService) servletContext.getAttribute("orderService");
        productService = (ProductService) servletContext.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession(false).getAttribute("profile");

        List<OrderDto> allOrders = orderService.getAllOrders(user.getId());
        List<String> allAddresses = new ArrayList<>();
        List<String> allProductsInOrder = new ArrayList<>();

        for (OrderDto order : allOrders) {
            String address;

            if (order.getDeliveryFlat() != null && !order.getDeliveryFlat().equals("")) {
                address = order.getDeliveryCity() + ", " + order.getDeliveryStreet() + ", " + order.getDeliveryHome() + ", " + order.getDeliveryFlat();
            } else {
                address = order.getDeliveryCity() + ", " + order.getDeliveryStreet() + ", " + order.getDeliveryHome();
            }

            allAddresses.add(address);


            String products = "";
            List<Long[]> list = orderService.getAllProductsInOrder(order.getId());

            for (Long[] l : list) {
                products += productService.getById(l[0]).getName() + " (" + l[1] + " items), ";
            }

            allProductsInOrder.add(products.substring(0, products.length() - 2));
        }

        req.setAttribute("productsInOrder", allProductsInOrder);
        req.setAttribute("orders", allOrders);
        req.setAttribute("addresses", allAddresses);

        req.getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }
}
