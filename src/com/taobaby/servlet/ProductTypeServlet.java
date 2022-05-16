package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.ProductTypeService;
import com.taobaby.service.impl.ProductTypeServiceImpl;
import com.taobaby.utils.IconfontUtils;
import com.taobaby.utils.UUIDUtils;

import javax.servlet.ServletException;
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
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<ProductType> pageData = getPageInfo(req, resp);
            pageData = productTypeService.productTypePage(pageData.getPageNum(), pageData.getPageSize());
            List<String> iconfonts = IconfontUtils.getIconfonts(req);
            req.getSession().setAttribute("iconfonts", iconfonts);
            req.getSession().setAttribute("productTypePages", pageData);
            forward("/admin/product_type/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }

    /**
     * 重定向到addPage页面
     * @param req
     * @param resp
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
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
            String msg = productTypeService.delProductType(productTypeName);
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

    /**
     * 删除选择的商品分类
     * @param req
     * @param resp
     */
    public void deleteSelect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String productTypeNames = req.getParameter("productTypeNames");
            String[] productTypeNameList = productTypeNames.split(",");
            String msg = productTypeService.delSelectProductType(productTypeNameList);
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

    /**
     * 修改页面
     * @param req
     * @param resp
     */
    public void updatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String productTypeName = req.getParameter("productTypeName");
            ProductType productType = productTypeService.getProductType(productTypeName);
            req.setAttribute("productType", productType);
            forward("/admin/product_type/update.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }

    /**
     * 修改商品类型
     * @param req
     * @param resp
     * @throws IOException
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String productTypeId = req.getParameter("id");
            String productTypeName = req.getParameter("productTypeName");
            String productTypeDesc = req.getParameter("productTypeDesc");
            String productTypeIcon = req.getParameter("productTypeIcon");
            ProductType productType = new ProductType(productTypeId, productTypeName, productTypeDesc, productTypeIcon);
            String msg = productTypeService.updateProductType(productType);
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
