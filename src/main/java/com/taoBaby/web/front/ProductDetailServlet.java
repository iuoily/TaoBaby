package com.taoBaby.web.front;

import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.Page;
import com.taoBaby.pojo.Product;
import com.taoBaby.service.OrderProductService;
import com.taoBaby.service.ProductService;
import com.taoBaby.service.impl.OrderProductServiceImpl;
import com.taoBaby.service.impl.ProductServiceImpl;
import com.taoBaby.utils.SpringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/front/productDetail/*")
public class ProductDetailServlet extends BaseServlet {

    private final ProductService productService = SpringUtils.getBean(ProductService.class);
    private final OrderProductService orderProductService = SpringUtils.getBean(OrderProductService.class);

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
