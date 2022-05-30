package com.taoBaby.common;

import com.taoBaby.utils.CodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/19.
 */

@WebServlet(value = "/common/getVerificationCode",loadOnStartup = 1)
public class GetVerificationCode extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = CodeUtil.getCerificationCode();
        req.getSession().setAttribute("VerificationCode", code);
        System.out.println("生成：" + code);
        resp.getWriter().write(code);
    }
}
