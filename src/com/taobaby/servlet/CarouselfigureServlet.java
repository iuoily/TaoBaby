package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.CarouselFigure;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.CarouselfigureService;
import com.taobaby.service.impl.CarouselfigureServiceImpl;
import com.taobaby.utils.IconfontUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/carouselfigure/*")
public class CarouselfigureServlet extends BaseServlet {

    CarouselfigureService carouselfigureService = new CarouselfigureServiceImpl();
    /**
     * 获取轮播图列表
     * @param req
     * @param resp
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<CarouselFigure> pageData = getPageInfo(req, resp);
            pageData = carouselfigureService.getCarouselfigurePage(pageData.getPageNum(), pageData.getPageSize());
            req.setAttribute("CarouselFigurePages", pageData);
            forward("/admin/carouselfigure_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }
}
