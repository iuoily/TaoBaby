package com.taobaby.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.BrandSerivce;
import com.taobaby.service.ProductService;
import com.taobaby.service.ProductTypeService;
import com.taobaby.service.impl.BrandSerivceImpl;
import com.taobaby.service.impl.ProductServiceImpl;
import com.taobaby.service.impl.ProductTypeServiceImpl;
import com.taobaby.utils.UUIDUtils;

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

    private ProductService productService = new ProductServiceImpl();
    private ProductTypeService productTypeService = new ProductTypeServiceImpl();
    private BrandSerivce brandSerivce = new BrandSerivceImpl();

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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
