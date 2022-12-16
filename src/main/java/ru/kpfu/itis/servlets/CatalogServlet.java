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
import ru.kpfu.itis.services.CartService;
import ru.kpfu.itis.services.OrderService;
import ru.kpfu.itis.services.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    private ProductService productService;
    private CartService cartService;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        productService = (ProductService) servletContext.getAttribute("productService");
        cartService = (CartService) servletContext.getAttribute("cartService");
        orderService = (OrderService) servletContext.getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("profile");

        String productFilter = req.getParameter("productFilter");
        List<ProductDto> products;

        if (productFilter != null) {
            switch (productFilter) {
                case ("ascending"):
                    products = productService.sortProductsByPriceIncreasing();
                    req.setAttribute("products", products);
                    break;
                case ("descending"):
                    products = productService.sortProductsByPriceDecreasing();
                    req.setAttribute("products", products);
                    break;
                case ("figures"):
                    products = productService.filterFigures();
                    req.setAttribute("products", products);
                    break;
                case ("manga"):
                    products = productService.filterManga();
                    req.setAttribute("products", products);
                    break;
                case ("snacks"):
                    products = productService.filterSnacks();
                    req.setAttribute("products", products);
                    break;
                default:
                    products = productService.getAll();
                    req.setAttribute("products", products);
                    break;
            }
        } else {
            products = productService.getAll();
            req.setAttribute("products", products);
        }

        req.getSession(false).setAttribute("count", cartService.getAllProductsInCart(user.getId()).size());
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productToAddInCart = req.getParameter("addToCart");
        UserDto user = (UserDto) req.getSession().getAttribute("profile");
        Integer count = (Integer) req.getSession(false).getAttribute("count");

        String productToDeleteFromCatalog = req.getParameter("deleteProduct");

        if (productToAddInCart != null) {
            cartService.addToCart(user.getId(), Long.valueOf(productToAddInCart));
            req.getSession(false).setAttribute("count", ++count);
        }

        if (productToDeleteFromCatalog != null && !orderService.isProductInOrder(Long.valueOf(productToDeleteFromCatalog))) {
            cartService.deleteProductFromCart(user.getId(), Long.valueOf(productToDeleteFromCatalog));
            productService.delete(Long.valueOf(productToDeleteFromCatalog));
        }

        resp.sendRedirect("/store/catalog");
    }
}
