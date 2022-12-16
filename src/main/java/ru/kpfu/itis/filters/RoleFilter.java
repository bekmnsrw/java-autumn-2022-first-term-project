package ru.kpfu.itis.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/add-product", "/upload-image", "/update-product"})
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        boolean isAdmin = false;
        boolean sessionExists = session != null;
        boolean isAuthenticated = false;

        if (sessionExists) {
            isAuthenticated = session.getAttribute("profile") != null;
        }

        if (isAuthenticated) {
            isAdmin = (boolean) session.getAttribute("role");
        }

        if (!isAdmin) {
            response.sendRedirect("/store/profile");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
