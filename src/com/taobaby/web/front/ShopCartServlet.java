package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/front/shopCart/*")
public class ShopCartServlet extends BaseServlet {

    public void shopCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/front/shop_cart/shop_cart.jsp", req, resp);
    }
}
