package com.taoBaby.web.admin;

import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.Brand;
import com.taoBaby.pojo.Page;
import com.taoBaby.pojo.ProductType;
import com.taoBaby.service.BrandSerivce;
import com.taoBaby.service.ProductTypeService;
import com.taoBaby.service.impl.BrandSerivceImpl;
import com.taoBaby.service.impl.ProductTypeServiceImpl;
import com.taoBaby.utils.SpringUtils;
import com.taoBaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
@WebServlet(value = "/admin/brand/*", loadOnStartup = 0)
public class BrandServlet extends BaseServlet {

    private final BrandSerivce brandSerivce = SpringUtils.getBean(BrandSerivce.class);
    private final ProductTypeService productTypeService = SpringUtils.getBean(ProductTypeService.class);

    /**
     * 获取品牌列表
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<Brand> pageInfo = getPageInfo(req, resp);
            pageInfo =  brandSerivce.getBrandPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<ProductType> productTypes = productTypeService.getProductTypes();
            req.getSession().setAttribute("BrandPages", pageInfo);
            req.getSession().setAttribute("productTypes", productTypes);
            forward("/admin/brand_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }

    /**
     * 跳转到添加用户界面
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/admin/brand_info/add.jsp", req, resp);
    }

    /**
     * 添加品牌
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = UUIDUtils.getId();
            String brandName = req.getParameter("brandName");
            String brandType = req.getParameter("brandType");
            String brandImg = req.getParameter("brandImg");
            String msg = brandSerivce.addBrand(new Brand(id, brandName, brandType, brandImg));
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
     * 删除品
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String msg = brandSerivce.deleteBrand(id);
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
     * 删除选中品牌
     */
    public void deleteSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String ids = req.getParameter("ids");
            String[] idls = ids.split(",");
            String msg = brandSerivce.deleteSelectBrand(idls);
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
     * 跳转到修改品牌界面
     */
    public void updatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            Brand brand = brandSerivce.getBrandById(id);
            req.setAttribute("brand", brand);
            forward("/admin/brand_info/update.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }

    }

    /**
     * 修改品牌
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String brandName = req.getParameter("brandName");
            String brandType = req.getParameter("brandType");
            String brandImg = req.getParameter("brandImg");
            String msg = brandSerivce.updateBrand(new Brand(id, brandName, brandType, brandImg));
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
