package com.taoBaby.web.front;

import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.Brand;
import com.taoBaby.pojo.Product;
import com.taoBaby.pojo.ProductType;
import com.taoBaby.service.BrandSerivce;
import com.taoBaby.service.OrderProductService;
import com.taoBaby.service.ProductService;
import com.taoBaby.service.ProductTypeService;
import com.taoBaby.service.impl.BrandSerivceImpl;
import com.taoBaby.service.impl.OrderProductServiceImpl;
import com.taoBaby.service.impl.ProductServiceImpl;
import com.taoBaby.service.impl.ProductTypeServiceImpl;
import com.taoBaby.utils.SpringUtils;

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

    private final ProductTypeService productTypeService = SpringUtils.getBean(ProductTypeService.class);
    private final BrandSerivce brandSerivce = SpringUtils.getBean(BrandSerivce.class);
    private final ProductService productService = SpringUtils.getBean(ProductService.class);
    private final OrderProductService orderProductService = SpringUtils.getBean(OrderProductService.class);

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
