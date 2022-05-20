package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front/receiveingAddress/*")
public class ReceiveingAddressServlet extends BaseServlet {

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/front/receiving_address/deliverAddress.jsp", req, resp);
    }
}
