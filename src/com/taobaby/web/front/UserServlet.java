package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.User;
import com.taobaby.service.UserService;
import com.taobaby.service.impl.UserServiceImpl;
import com.taobaby.utils.EncryptionUtils;
import com.taobaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/19.
 */

@WebServlet("/front/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        resp.sendRedirect("/front/index");
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object verificationCode = req.getSession().getAttribute("VerificationCode");
            String code = req.getParameter("code");
            if (!code.equalsIgnoreCase((String) verificationCode)) {
                resp.getWriter().write("验证码错误！");
                return;
            }
            String name = req.getParameter("username");
            String password = req.getParameter("password");
            String msg = userService.login(name, password, 1);
            if (msg != null) {
                resp.getWriter().write(msg);
            } else {
                String autoLogin = req.getParameter("autoLogin");
                if (autoLogin!=null) {
                    Cookie cookie = new Cookie("JSESSIONID", req.getSession().getId());
                    cookie.setMaxAge(60*60*24*7);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                } else {
                    Cookie cookie = new Cookie("JSESSIONID", req.getSession().getId());
                    cookie.setMaxAge(-1);
                    resp.addCookie(cookie);
                }
                req.getSession().setAttribute("user", userService.getUserByName(name, 1));
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("发生异常！");
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object verificationCode = req.getSession().getAttribute("VerificationCode");
            String code = req.getParameter("code");
            if (!code.equalsIgnoreCase((String) verificationCode)) {
                resp.getWriter().write("验证码错误！");
                return;
            }
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String msg = userService.addUser(new User(UUIDUtils.getId(), name, EncryptionUtils.encryptMD5(password), 1));
            if (msg != null) {
                resp.getWriter().write(msg);
            } else {
                resp.getWriter().write("ok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("发生异常！");
        }


    }
}
