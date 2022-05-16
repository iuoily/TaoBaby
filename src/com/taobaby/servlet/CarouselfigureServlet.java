package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.CarouselFigure;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.CarouselfigureService;
import com.taobaby.service.impl.CarouselfigureServiceImpl;
import com.taobaby.utils.IconfontUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/admin/carouselfigure/*")
public class CarouselfigureServlet extends BaseServlet {

    CarouselfigureService carouselfigureService = new CarouselfigureServiceImpl();
    /**
     * 获取轮播图列表
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            Page<CarouselFigure> pageData = getPageInfo(req, resp);
//            pageData = carouselfigureService.productTypePage(pageData.getPageNum(), pageData.getPageSize());
//            List<String> iconfonts = IconfontUtils.getIconfonts(req);
//            req.getSession().setAttribute("iconfonts", iconfonts);
//            req.getSession().setAttribute("productTypePages", pageData);
//            forward("/admin/product_type/list.jsp", req, resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
