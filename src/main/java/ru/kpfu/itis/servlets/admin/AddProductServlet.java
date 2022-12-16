package ru.kpfu.itis.servlets.admin;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.dto.forms.ProductForm;
import ru.kpfu.itis.services.ProductService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@WebServlet("/add-product")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        productService = (ProductService) servletContext.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/add_product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String productDescription = req.getParameter("productDescription");
        String productPrice = req.getParameter("productPrice");
        String productCategory = req.getParameter("productCategory");
        Part image = req.getPart("productImage");

        ProductForm productForm = new ProductForm(productName, productDescription, Long.valueOf(productPrice), productCategory);

        ProductDto product = productService.saveProduct(productForm);

        if (image.getInputStream() instanceof FileInputStream) {
            try (InputStream inputStream = (image.getInputStream())) {
                new File("..\\ImagesForWebSite\\ProductImages").mkdirs();
                File file = new File("..\\ImagesForWebSite\\ProductImages\\" + product.getId() + ".png");
                Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }

        resp.sendRedirect("add-product");
    }
}
