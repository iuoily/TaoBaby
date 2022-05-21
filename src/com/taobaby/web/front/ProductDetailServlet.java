package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Product;
import com.taobaby.service.ProductService;
import com.taobaby.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front/productDetail/*")
public class ProductDetailServlet extends BaseServlet {

    private ProductService productService = new ProductServiceImpl();

    public void productDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            Product productById = productService.getProductById(id);
            req.setAttribute("product", productById);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/product_detail/product_detail.jsp", req, resp);
    }
}
