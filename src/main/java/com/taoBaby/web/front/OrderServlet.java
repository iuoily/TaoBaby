package com.taoBaby.web.front;

import com.alibaba.fastjson.JSONObject;
import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.*;
import com.taoBaby.service.*;
import com.taoBaby.service.impl.*;
import com.taoBaby.utils.SpringUtils;
import com.taoBaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@WebServlet("/front/order/*")
public class OrderServlet extends BaseServlet {

    private final OrderService orderService = SpringUtils.getBean(OrderService.class);
    private final OrderProductService orderProductService = SpringUtils.getBean(OrderProductService.class);
    private final ReceiveingAddressService receiveingAddressService = SpringUtils.getBean(ReceiveingAddressService.class);

    /**
     * 跳转到订单页面
     */
    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            List<Order> listOrder = orderService.listOrder(user.getId());
            req.setAttribute("listOrder", listOrder);
            List<ReceivingAddress> receivingAddresses = receiveingAddressService.listAddress(user.getId());
            req.setAttribute("receivingAddressesList", receivingAddresses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/order_page/order.jsp", req, resp);
    }

    /**
     * 根据id删除订单
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String msg = orderService.removeOrder(id);
        resp.getWriter().write(msg);
    }

    /**
     * 添加到购物车
     */
    public void compute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            String addressId = req.getParameter("address_id");
            String[] products = req.getParameterValues("products");
            String product = req.getParameter("products");
            String orderId = orderService.addOrder(new Order(UUIDUtils.getId(), LocalDateTime.now(), addressId, user.getId()));
            List<Map> lists = JSONObject.parseArray(product, Map.class);
            lists.forEach(a -> {
                OrderProduct orderProduct = new OrderProduct(UUIDUtils.getId(), orderId, (String) a.get("id"), Integer.parseInt((String) a.get("num")));
                try {
                    orderProductService.addOrderProduct(orderProduct);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(lists);
            resp.getWriter().write("ok");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("异常" + e.getMessage());
        }
    }

}
