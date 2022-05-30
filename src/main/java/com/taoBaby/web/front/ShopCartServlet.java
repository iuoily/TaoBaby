package com.taoBaby.web.front;

import com.taoBaby.common.BaseServlet;
import com.taoBaby.pojo.ReceivingAddress;
import com.taoBaby.pojo.ShopCart;
import com.taoBaby.pojo.ShopCartProduct;
import com.taoBaby.pojo.User;
import com.taoBaby.service.ReceiveingAddressService;
import com.taoBaby.service.ShopCartProductService;
import com.taoBaby.service.ShopCartService;
import com.taoBaby.service.impl.ReceiveingAddressServiceImpl;
import com.taoBaby.service.impl.ShopCartProductServiceImpl;
import com.taoBaby.service.impl.ShopCartServiceImpl;
import com.taoBaby.utils.SpringUtils;
import com.taoBaby.utils.UUIDUtils;

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

    private final ShopCartService shopCartService = SpringUtils.getBean(ShopCartService.class);
    private final ShopCartProductService shopCartProductService = SpringUtils.getBean(ShopCartProductService.class);
    private final ReceiveingAddressService receiveingAddressService = SpringUtils.getBean(ReceiveingAddressService.class);

    /**
     * 购物车界面
     */
    public void shopCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            ShopCart shopCart = shopCartService.getShopCart(user.getId());
            req.setAttribute("shopCartProductlist", shopCart.getShopCartProductList());
            List<ReceivingAddress> receivingAddresses = receiveingAddressService.listAddress(user.getId());
            req.setAttribute("receivingAddressesList", receivingAddresses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        forward("/front/shop_cart/shop_cart.jsp", req, resp);
    }

    /**
     * 添加到购物车
     */
    public void addProductToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            ShopCartProduct shopCartProduct = shopCartProductService.getShopCartProduct(productId, shopCart.getCartId());
            if (shopCartProduct != null) {
                shopCartProductService.removeShopCartProduct(shopCartProduct.getId());
                productNum += shopCartProduct.getProductNum();
            }
            shopCartProductService.addShopCartProduct(new ShopCartProduct(UUIDUtils.getId(), shopCart.getCartId(), productId, productNum));
            resp.getWriter().write("ok");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("异常"+ e.getMessage());
        }
    }

    /**
     * 删除购物车商品
     */
    public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            shopCartProductService.removeShopCartProduct(id);
            resp.getWriter().write("ok");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }
}
