package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Product;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.BrandSerivce;
import com.taobaby.service.OrderProductService;
import com.taobaby.service.ProductService;
import com.taobaby.service.ProductTypeService;
import com.taobaby.service.impl.BrandSerivceImpl;
import com.taobaby.service.impl.OrderProductServiceImpl;
import com.taobaby.service.impl.ProductServiceImpl;
import com.taobaby.service.impl.ProductTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author sun
 */
@WebServlet("/front/productType/*")
public class ProductTypeServlet extends BaseServlet {

    private final ProductTypeService productTypeService = new ProductTypeServiceImpl();
    private final BrandSerivce brandSerivce = new BrandSerivceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final OrderProductService orderProductService = new OrderProductServiceImpl();

    /**
     * 跳转类型分类页面
     */
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String type = req.getParameter("type");
            String brands = req.getParameter("brands");
            String words = req.getParameter("words");

            ProductType productType = productTypeService.getProductTypeName(type);
            List<Brand> brand = brandSerivce.getBrandByProductType(productType.getId());
            List<Product> datas = productService.getProductPage(1,10, words, productType.getId()).getPageData();
            datas.forEach(a-> {
                try {
                    a.setSales(orderProductService.getSalesNum(a.getId()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            req.getSession().setAttribute("datas", datas);

            req.getSession().setAttribute("productType2", productType);
            req.getSession().setAttribute("listBrand", brand);
            req.getSession().setAttribute("words", words);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/product_type/product_type.jsp", req, resp);
    }
}
