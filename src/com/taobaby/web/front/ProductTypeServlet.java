package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sun
 */
@WebServlet("/front/productType/*")
public class ProductTypeServlet extends BaseServlet {

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/front/product_type/product_type.jsp", req, resp);
    }
}
