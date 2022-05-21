package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.*;
import com.taobaby.service.*;
import com.taobaby.service.impl.*;
import com.taobaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/front/order/*")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();
    private ReceiveingAddressService receiveingAddressService = new ReceiveingAddressServiceImpl();
    private ShopCartService shopCartService = new ShopCartServiceImpl();
    private ShopCartProductService shopCartProductService = new ShopCartProductServiceImpl();

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
    public void addToShopCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            String productId = req.getParameter("productId");
            int productNum = Integer.parseInt(req.getParameter("productNum"));
            ShopCart shopCart = shopCartService.getShopCart(user.getId());
            String msg = "";
            if (shopCart == null) {
                String id = UUIDUtils.getId();
                shopCart = new ShopCart(id, id, user.getId());
                msg = shopCartService.addShopCart(shopCart);
            }
            String id = UUIDUtils.getId();
            msg += shopCartProductService.addShopCartProduct(new ShopCartProduct(id, shopCart.getCartId(), productId, productNum));
            resp.getWriter().write("ok");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("异常"+ e.getMessage());
        }
    }
}
