package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.User;
import com.taobaby.service.UserService;
import com.taobaby.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/11.
 */
@WebServlet("/admin/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 后台用户登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            String msg = userService.login(username, password);
            if (null != msg) {
                req.setAttribute("msg", msg);
                forward("admin/login/login.jsp", req, resp);
            } else {
                req.getSession().setAttribute("_admin", username);
                resp.sendRedirect("/admin/index/index.page");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", e.getMessage());
            forward("admin/login/login.jsp", req, resp);
        }
    }

    /**
     * 后台用户列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<User> pageInfo = getPageInfo(req, resp);
            pageInfo = userService.getUserPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            req.setAttribute("userPages", pageInfo);
            forward("/admin/user_info/list.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMessage", e.getMessage());
            forward("/500.jsp", req, resp);
        }
    }

    /**
     * 后台用户登出
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setMaxInactiveInterval(0);
        resp.sendRedirect("/admin/login/login.page");
    }

    /**
     * 获取后台用户修改密码页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void changePasswordPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/admin/user_info/changePassword.jsp", req, resp);
    }

    /**
     * 后台用户修改密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("_admin");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        System.out.println(username);
        System.out.println(oldPassword);
        System.out.println(newPassword);
        resp.getWriter().write("ok");
    }
}
