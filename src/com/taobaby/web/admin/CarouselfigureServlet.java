package com.taobaby.web.admin;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.CarouselFigure;
import com.taobaby.pojo.Page;
import com.taobaby.service.CarouselfigureService;
import com.taobaby.service.impl.CarouselfigureServiceImpl;
import com.taobaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/admin/carouselfigure/*", loadOnStartup = 0)
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
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
