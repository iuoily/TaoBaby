package com.taoBaby.web.admin;

import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.CarouselFigure;
import com.taoBaby.pojo.Page;
import com.taoBaby.service.BrandSerivce;
import com.taoBaby.service.CarouselfigureService;
import com.taoBaby.service.impl.CarouselfigureServiceImpl;
import com.taoBaby.utils.SpringUtils;
import com.taoBaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/carouselfigure/*", loadOnStartup = 0)
public class CarouselfigureServlet extends BaseServlet {

    private final CarouselfigureService carouselfigureService = SpringUtils.getBean(CarouselfigureService.class);
    /**
     * 获取轮播图列表
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<CarouselFigure> pageData = getPageInfo(req, resp);
            pageData = carouselfigureService.getCarouselfigurePage(pageData.getPageNum(), pageData.getPageSize());
            req.getSession().setAttribute("CarouselFigurePages", pageData);
            forward("/admin/carouselfigure_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }

    /**
     * 跳转到添加轮播图界面
     */
    public void addPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/admin/carouselfigure_info/add.jsp", req, resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = UUIDUtils.getId();
            Integer sequenceNum = Integer.parseInt(req.getParameter("sequenceNum"));
            String carouselfigureImg = req.getParameter("carouselfigureImg");
            String msg = carouselfigureService.addCarouselFigure(new CarouselFigure(id, carouselfigureImg, sequenceNum));
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
     * 删除轮播图
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String msg = carouselfigureService.deleteCarouselFigure(id);
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
     * 删除选中轮播图
     */
    public void deleteSelect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String ids = req.getParameter("ids");
            String[] idls = ids.split(",");
            String msg = carouselfigureService.deleteSelectCarouselFigure(idls);
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
     * 跳转到修改轮播图
     */
    public void updatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            CarouselFigure carouselFigure = carouselfigureService.getCarouselFigure(id);
            req.setAttribute("carouselfigure", carouselFigure);
            forward("/admin/carouselfigure_info/update.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }

    }

    /**
     * 修改轮播图
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            Integer sequenceNum = Integer.parseInt(req.getParameter("sequenceNum"));
            String url = req.getParameter("url");
            String msg = carouselfigureService.updateCarouselFigure(new CarouselFigure(id, url, sequenceNum));
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
