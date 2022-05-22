package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;
import com.taobaby.service.OrderProductService;
import com.taobaby.service.ProductService;
import com.taobaby.service.impl.OrderProductServiceImpl;
import com.taobaby.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/front/productDetail/*")
public class ProductDetailServlet extends BaseServlet {

    private ProductService productService = new ProductServiceImpl();
    private OrderProductService orderProductService = new OrderProductServiceImpl();

    /**
     * 跳转商品详情页
     */
    public void productDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            Product productById = productService.getProductById(id);
            int salesNum = orderProductService.getSalesNum(id);
            Page<Product> productPage = productService.getProductPage(productById.getProductType(), 1, 10);
            productPage.getPageData().forEach(a-> {
                try {
                    a.setSales(orderProductService.getSalesNum(a.getId()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            productById.setSales(salesNum);
            req.setAttribute("product", productById);
            req.setAttribute("list", productPage.getPageData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/product_detail/product_detail.jsp", req, resp);
    }
}
