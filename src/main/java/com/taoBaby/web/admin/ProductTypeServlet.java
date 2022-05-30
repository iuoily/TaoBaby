package com.taoBaby.web.admin;

import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.Page;
import com.taoBaby.pojo.ProductType;
import com.taoBaby.service.ProductTypeService;
import com.taoBaby.service.impl.ProductTypeServiceImpl;
import com.taoBaby.utils.IconfontUtils;
import com.taoBaby.utils.SpringUtils;
import com.taoBaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author iuoily on 2022/5/12.
 */

@WebServlet(value = "/admin/productType/*", loadOnStartup = 0)
public class ProductTypeServlet extends BaseServlet {

    private final ProductTypeService productTypeService = SpringUtils.getBean(ProductTypeService.class);

    /**
     * 获取商品分类列表
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
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.sendRedirect("/admin/product_type/add.page");
    }

    /**
     * 新增商品分类
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
