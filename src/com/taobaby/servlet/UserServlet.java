package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;
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

    public void temp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
    }

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

}
