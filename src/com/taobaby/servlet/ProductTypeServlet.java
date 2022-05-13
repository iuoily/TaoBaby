package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.ProductTypeService;
import com.taobaby.service.impl.ProductTypeServiceImpl;
import com.taobaby.utils.IconfontUtils;
import com.taobaby.utils.UUIDUtils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author iuoily on 2022/5/12.
 */

@WebServlet("/admin/productType/*")
public class ProductTypeServlet extends BaseServlet {

    private ProductTypeService productTypeService = new ProductTypeServiceImpl();

    /**
     * 获取商品分类列表
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Page<ProductType> pageData = getPageInfo(req, resp);
            pageData = productTypeService.productTypePage(pageData.getPageNum(), pageData.getPageSize());
            req.setAttribute("productTypePages", pageData);
            req.getRequestDispatcher("/admin/product_type/list.page").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载图标库到addPage页面
     * @param req
     * @param resp
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<String> iconfonts = IconfontUtils.getIconfonts(req);
            req.getSession().setAttribute("iconfonts", iconfonts);
            resp.sendRedirect("/admin/product_type/add.page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增商品分类
     * @param req
     * @param resp
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String productTypeName = req.getParameter("productTypeName");
            String productTypeDesc = req.getParameter("productTypeDesc");
            String productTypeIcon = req.getParameter("productTypeIcon");
            String productTypeId = UUIDUtils.getId();

            ProductType productType = new ProductType(productTypeId, productTypeName, productTypeDesc, productTypeIcon);
            String msg = productTypeService.addProductType(productType);
            if (msg != null) {
                resp.getWriter().write(msg);
                return;
            }
            resp.getWriter().write("ok");
        } catch (Exception e) {
            resp.getWriter().write(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除商品
     * @param req
     * @param resp
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String productTypeName = req.getParameter("productTypeName");
            System.out.println(productTypeName);
            String msg = productTypeService.delProductType(productTypeName);
            System.out.println(msg);
            if (msg != null) {
                resp.getWriter().write(msg);
                return;
            }
            resp.getWriter().write("ok");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

}
