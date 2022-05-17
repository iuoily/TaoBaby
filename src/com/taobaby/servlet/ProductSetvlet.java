package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.ProductService;
import com.taobaby.service.ProductTypeService;
import com.taobaby.service.impl.ProductServiceImpl;
import com.taobaby.service.impl.ProductTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */

@WebServlet("/admin/product/*")
public class ProductSetvlet extends BaseServlet {

    private ProductService productService = new ProductServiceImpl();
    private ProductTypeService productTypeService = new ProductTypeServiceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<Product> pageInfo = getPageInfo(req, resp);
            pageInfo = productService.getProductPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<ProductType> productTypes = productTypeService.getProductTypes();
            req.getSession().setAttribute("BrandPages", pageInfo);
            req.setAttribute("productPages", pageInfo);
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
}
