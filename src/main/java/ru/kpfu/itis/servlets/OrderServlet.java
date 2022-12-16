package ru.kpfu.itis.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.dto.forms.OrderForm;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.services.CartService;
import ru.kpfu.itis.services.OrderMapper;
import ru.kpfu.itis.services.OrderService;
import ru.kpfu.itis.utils.OrderTime;

import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private CartService cartService;
    private OrderService orderService;
    private OrderMapper orderMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        cartService = (CartService) servletContext.getAttribute("cartService");
        orderService = (OrderService) servletContext.getAttribute("orderService");
        orderMapper = (OrderMapper) servletContext.getAttribute("orderMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("profile");

        req.setAttribute("totalOrderPrice", cartService.getTotalOrderPrice(user.getId()));
        req.setAttribute("totalOrderAmount", cartService.getTotalOrderAmount(user.getId()));
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deliveryCity = req.getParameter("deliveryCity");
        String deliveryStreet = req.getParameter("deliveryStreet");
        String deliveryHome = req.getParameter("deliveryHome");
        String deliveryFlat = req.getParameter("deliveryFlat");

        UserDto user = (UserDto) req.getSession().getAttribute("profile");
        Long totalOrderPrice = cartService.getTotalOrderPrice(user.getId());

        OrderTime orderTime = new OrderTime();
        String date = orderTime.getCurrentTime();

        OrderForm orderForm = new OrderForm(deliveryCity, deliveryStreet, deliveryHome, deliveryFlat);
        Order order = orderMapper.toOrderFromOrderForm(orderForm, date, totalOrderPrice, user.getId());

        Order order1 = orderService.makeOrder(order);

        List<ProductDto> productsInCart = cartService.getAllProductsInCart(user.getId());

        for (ProductDto product : productsInCart) {
            orderService.saveOrderProductConnectivity(order1.getId(), product.getId(), cartService.getProductAmountInCart(user.getId(), product.getId()));
        }

        cartService.clearCart(user.getId());

        resp.sendRedirect("profile");
    }
}
