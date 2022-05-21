package com.taobaby.service.impl;

import com.taobaby.dao.ShopCartDao;
import com.taobaby.dao.impl.ShopCartDaoImpl;
import com.taobaby.dao.impl.ShopCartProductDaoImpl;
import com.taobaby.pojo.ShopCart;
import com.taobaby.service.ShopCartService;
import com.taobaby.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly create on 2022/5/22
 */
public class ShopCartServiceImpl implements ShopCartService {

    private ShopCartDao shopCartDao = null;

    @Override
    public ShopCart getShopCart(String userId) throws Exception {
        Connection conn = DBUtils.getConn();
        shopCartDao = new ShopCartDaoImpl(conn);
        ShopCart shopCart = shopCartDao.queryShopCart(userId);
        shopCart.setShopCartProductList(new ShopCartProductDaoImpl(conn).listShopCartProduct(shopCart.getCartId()));
        DBUtils.close(conn);
        return shopCart;
    }

    @Override
    public String removeShopCart(String cartId) {
        Connection conn = null;
        try {
            conn = DBUtils.getConn();
            conn.setAutoCommit(false);
            shopCartDao = new ShopCartDaoImpl(conn);
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
        shopCartDao = new ShopCartDaoImpl(conn);
        shopCartDao.insertShopCart(shopCart);
        DBUtils.close(conn);
        return null;
    }
}
