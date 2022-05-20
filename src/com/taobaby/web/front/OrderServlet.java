package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front/order/*")
public class OrderServlet extends BaseServlet {

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/front/order_page/order.jsp", req, resp);
    }
}
