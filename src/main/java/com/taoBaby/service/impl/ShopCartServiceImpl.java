package com.taoBaby.service.impl;

import com.taoBaby.dao.ShopCartDao;
import com.taoBaby.dao.impl.ShopCartDaoImpl;
import com.taoBaby.dao.impl.ShopCartProductDaoImpl;
import com.taoBaby.pojo.ShopCart;
import com.taoBaby.service.ShopCartService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;
import com.taoBaby.utils.UUIDUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author iuoly create on 2022/5/22
 */
public class ShopCartServiceImpl implements ShopCartService {

    private ShopCartDao shopCartDao = null;

    @Override
    public ShopCart getShopCart(String userId) throws Exception {
        Connection conn = DBUtils.getConn();
        shopCartDao = SpringUtils.getBean(ShopCartDao.class);
        ShopCart shopCart = shopCartDao.queryShopCart(userId);
        if (shopCart != null) {
            shopCart.setShopCartProductList(new ShopCartProductDaoImpl(conn).listShopCartProduct(shopCart.getCartId()));
        }
        String id = UUIDUtils.getId();
        shopCartDao.insertShopCart(new ShopCart(id,id,userId));
        DBUtils.close(conn);
        return shopCart;
    }

    @Override
    public String removeShopCart(String cartId) {
        Connection conn = null;
        try {
            conn = DBUtils.getConn();
            conn.setAutoCommit(false);
            shopCartDao = SpringUtils.getBean(ShopCartDao.class);
            shopCartDao.deleteShopCart(cartId);
            ShopCartProductDaoImpl shopCartProductDao = new ShopCartProductDaoImpl(conn);
            shopCartProductDao.deleteShopCartProduct(cartId);
            conn.commit();
            conn.setAutoCommit(true);
            return "ok";
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return "异常";
        } finally {
            DBUtils.close(conn);
        }
    }

    @Override
    public String addShopCart(ShopCart shopCart) throws SQLException {
        Connection conn = DBUtils.getConn();
        shopCartDao = SpringUtils.getBean(ShopCartDao.class);
        shopCartDao.insertShopCart(shopCart);
        DBUtils.close(conn);
        return null;
    }
}
