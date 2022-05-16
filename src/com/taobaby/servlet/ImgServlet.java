package com.taobaby.servlet;

import com.taobaby.common.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/16.
 */

@WebServlet("/common/*")
public class ImgServlet extends BaseServlet {

    public void getImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagepath = req.getParameter("image");
        System.out.println(imagepath);
        req.getRequestDispatcher("/static/img/banner01.jpg").forward(req,resp);
    }
}
