package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;
import com.taobaby.service.ProductService;
import com.taobaby.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/16.
 */

@WebServlet("/admin/product/*")
public class ProductSetvlet extends BaseServlet {

    private ProductService productService = new ProductServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<Product> pageInfo = getPageInfo(req, resp);
            pageInfo = productService.getProductPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            req.setAttribute("productPages", pageInfo);
            forward("/admin/product_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }
}
