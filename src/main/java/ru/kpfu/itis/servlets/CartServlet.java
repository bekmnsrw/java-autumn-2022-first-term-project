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
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.CartService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        cartService = (CartService) servletContext.getAttribute("cartService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("profile");

        List<Long> amount = new ArrayList<>();
        List<ProductDto> allProductsInCart = cartService.getAllProductsInCart(user.getId());

        for (ProductDto productDto : allProductsInCart) {
            amount.add(cartService.getProductAmountInCart(user.getId(), productDto.getId()));
        }

        req.getSession(false).setAttribute("count", allProductsInCart.size());
        req.setAttribute("productsInCart", allProductsInCart);
        req.setAttribute("amount", amount);
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productToDeleteFromCart = req.getParameter("deleteFromCart");
        UserDto user = (UserDto) req.getSession().getAttribute("profile");
        Integer count = (Integer) req.getSession(false).getAttribute("count");

        String btnPlus = req.getParameter("btnPlus");
        String btnMinus = req.getParameter("btnMinus");

        if (btnPlus != null) {
            cartService.increaseProductAmount(user.getId(), Long.valueOf(btnPlus));
        }

        if (btnMinus != null) {
            if (cartService.isProductAmountEqualsOne(user.getId(), Long.valueOf(btnMinus))) {
                cartService.deleteProductFromCart(user.getId(), Long.valueOf(btnMinus));
            } else {
                cartService.decreaseProductAmount(user.getId(), Long.valueOf(btnMinus));
            }
        }

        if (productToDeleteFromCart != null) {
            cartService.deleteProductFromCart(user.getId(), Long.valueOf(productToDeleteFromCart));
            req.getSession(false).setAttribute("count", --count);
        }

        resp.sendRedirect("/store/cart");
    }
}
