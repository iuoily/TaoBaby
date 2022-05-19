package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.User;
import com.taobaby.service.UserService;
import com.taobaby.service.impl.UserServiceImpl;
import com.taobaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/19.
 */

@WebServlet("/front/user/*")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object verificationCode = req.getSession().getAttribute("VerificationCode");
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object verificationCode = req.getSession().getAttribute("VerificationCode");
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String code = req.getParameter("code");
            if (!code.equals(verificationCode)) {
                resp.getWriter().write("验证码错误！");
                return;
            }
            String msg = userService.addUser(new User(UUIDUtils.getId(), name, password, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
