package com.taoBaby.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.Brand;
import com.taoBaby.pojo.Page;
import com.taoBaby.pojo.Product;
import com.taoBaby.pojo.ProductType;
import com.taoBaby.service.BrandSerivce;
import com.taoBaby.service.ProductService;
import com.taoBaby.service.ProductTypeService;
import com.taoBaby.service.impl.BrandSerivceImpl;
import com.taoBaby.service.impl.ProductServiceImpl;
import com.taoBaby.service.impl.ProductTypeServiceImpl;
import com.taoBaby.utils.SpringUtils;
import com.taoBaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */

@WebServlet(value = "/admin/product/*", loadOnStartup = 0)
public class ProductSetvlet extends BaseServlet {

    private final ProductService productService = SpringUtils.getBean(ProductService.class);
    private final ProductTypeService productTypeService = SpringUtils.getBean(ProductTypeService.class);
    private final BrandSerivce brandSerivce = SpringUtils.getBean(BrandSerivce.class);

    /**
     * 转跳商品列表页面
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<Product> pageInfo = getPageInfo(req, resp);
            String productName = req.getParameter("productName");
            String productType = req.getParameter("productType");
            if (!StringUtils.isNullOrEmpty(productType) && !StringUtils.isNullOrEmpty(productName)) {
                pageInfo = productService.getProductPage(pageInfo.getPageNum(), pageInfo.getPageSize(), productName, productType);
            } else if (!StringUtils.isNullOrEmpty(productType)){
                pageInfo = productService.getProductPage(productType, pageInfo.getPageNum(), pageInfo.getPageSize());
            } else if (!StringUtils.isNullOrEmpty(productName)){
                pageInfo = productService.getProductPage(pageInfo.getPageNum(), pageInfo.getPageSize(),productName);
            } else {
                pageInfo = productService.getProductPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            }
            List<ProductType> productTypes = productTypeService.getProductTypes();
            req.getSession().setAttribute("productName", productName);
            req.getSession().setAttribute("type", productType);
            req.getSession().setAttribute("productPages", pageInfo);
            req.setAttribute("productTypes", productTypes);
            forward("/admin/product_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }


    /**
     * 删除商品
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String msg = productService.deleteProduct(id);
            if (null != msg) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 删除选中商品
     */
    public void deleteSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String ids = req.getParameter("ids");
            String[] idls = ids.split(",");
            String msg = productService.deleteSelectProduct(idls);
            if (null != msg) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 跳转到添加商品界面
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ProductType> productTypes = productTypeService.getProductTypes();
            req.getSession().setAttribute("productTypes", productTypes);
            forward("/admin/product_info/add.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加商品
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = UUIDUtils.getId();
            String productName = req.getParameter("productName");
            String productImage = req.getParameter("productImage");
            double price = Double.parseDouble(req.getParameter("price"));
            String productType = req.getParameter("productType");
            String productDesc = req.getParameter("productDesc");
            String productBrand = req.getParameter("productBrand");
            LocalDateTime timestamp = LocalDateTime.now();
            String msg = productService.addProduct(new Product(id, productName, productImage, price, productType, productDesc, timestamp, productBrand));
            if (null != msg) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    /**
     * 根据商品类型查询品牌
     */
    public void getBrandByProductType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String productTypeId = req.getParameter("productTypeId");
            List<Brand> brandByProductType = brandSerivce.getBrandByProductType(productTypeId);
            String json = JSONObject.toJSONString(brandByProductType);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到修改商品界面
     */
    public void updatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            Product product = productService.getProductById(id);
            List<ProductType> productTypes = productTypeService.getProductTypes();
            req.getSession().setAttribute("productTypes", productTypes);
            req.setAttribute("product", product);
            forward("/admin/product_info/update.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }

    }

    /**
     * 修改商品
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String productName = req.getParameter("productName");
            String productImage = req.getParameter("productImage");
            double price = Double.parseDouble(req.getParameter("price"));
            String productType = req.getParameter("productType");
            String productDesc = req.getParameter("productDesc");
            String productBrand = req.getParameter("productBrand");
            String msg = productService.updateProduct(new Product(id, productName, productImage, price, productType, productDesc, null, productBrand));
            if (null != msg) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }
}
