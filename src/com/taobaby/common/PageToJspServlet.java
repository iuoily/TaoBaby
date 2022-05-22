package com.taobaby.common;

import com.taobaby.common.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/11.
 */

@WebServlet(value = "*.page", loadOnStartup = 0)
public class PageToJspServlet extends BaseServlet {

    /**
     * 解析.page路径到.jsp
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().replace(".page",".jsp");
        System.out.println(url);
        forward(url, req, resp);
    }
}
