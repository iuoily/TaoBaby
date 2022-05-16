package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Page;
import com.taobaby.service.BrandSerivce;
import com.taobaby.service.impl.BrandSerivceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/16.
 */
@WebServlet("/admin/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandSerivce brandSerivce = new BrandSerivceImpl();

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<Brand> pageInfo = getPageInfo(req, resp);
            pageInfo =  brandSerivce.getBrandPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            req.setAttribute("BrandPages", pageInfo);
            forward("/admin/brand_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }
}
