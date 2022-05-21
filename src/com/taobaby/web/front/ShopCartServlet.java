package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.ReceivingAddress;
import com.taobaby.pojo.ShopCart;
import com.taobaby.pojo.ShopCartProduct;
import com.taobaby.pojo.User;
import com.taobaby.service.ReceiveingAddressService;
import com.taobaby.service.ShopCartProductService;
import com.taobaby.service.ShopCartService;
import com.taobaby.service.impl.ReceiveingAddressServiceImpl;
import com.taobaby.service.impl.ShopCartProductServiceImpl;
import com.taobaby.service.impl.ShopCartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author iuoily on 2022/5/19.
 */
@WebServlet("/front/shopCart/*")
public class ShopCartServlet extends BaseServlet {

    private ShopCartService shopCartService = new ShopCartServiceImpl();
    private ShopCartProductService shopCartProductService = new ShopCartProductServiceImpl();
    private ReceiveingAddressService receiveingAddressService = new ReceiveingAddressServiceImpl();

    public void shopCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            ShopCart shopCart = shopCartService.getShopCart(user.getId());
            List<ShopCartProduct> shopCartProducts = shopCartProductService.listShopCartProduct(shopCart.getCartId());
            req.setAttribute("shopCartProductlist", shopCartProducts);
            List<ReceivingAddress> receivingAddresses = receiveingAddressService.listAddress(user.getId());
            req.setAttribute("receivingAddressesList", receivingAddresses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/shop_cart/shop_cart.jsp", req, resp);
    }
}
