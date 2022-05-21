package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Product;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.BrandSerivce;
import com.taobaby.service.ProductService;
import com.taobaby.service.ProductTypeService;
import com.taobaby.service.impl.BrandSerivceImpl;
import com.taobaby.service.impl.ProductServiceImpl;
import com.taobaby.service.impl.ProductTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author sun
 */
@WebServlet("/front/productType/*")
public class ProductTypeServlet extends BaseServlet {

    private ProductTypeService productTypeService = new ProductTypeServiceImpl();
    private BrandSerivce brandSerivce = new BrandSerivceImpl();
    private ProductService productService = new ProductServiceImpl();

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String type = req.getParameter("type");
            String brands = req.getParameter("brands");
            ProductType productType = productTypeService.getProductTypeName(type);
            List<Brand> brand = brandSerivce.getBrandByProductType(productType.getId());
            /**
             *
             */
            List<Product> newProducts = productService.getNewProducts(10);
            req.getSession().setAttribute("datas", newProducts);


            req.getSession().setAttribute("productType2", productType);
            req.getSession().setAttribute("listBrand", brand);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/product_type/product_type.jsp", req, resp);
    }
}
