package com.taoBaby.dao.impl;

import com.taoBaby.dao.ShopCartDao;
import com.taoBaby.pojo.ShopCart;
import com.taoBaby.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author iuoly create on 2022/5/22
 */
public class ShopCartDaoImpl implements ShopCartDao {

    Connection conn;

    public ShopCartDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ShopCart queryShopCart(String userId) throws Exception {
        return DBUtils.getBean(conn, ShopCart.class, "select * from s_shop_cart where user_id = ?", userId);
    }

    @Override
    public void insertShopCart(ShopCart shopCart) throws SQLException {
        DBUtils.execute(conn, "insert  into s_shop_cart(id,cart_id,user_id) values (?,?,?)", shopCart.getId(), shopCart.getCartId(), shopCart.getUserId());
    }

    @Override
    public void deleteShopCart(String id) throws SQLException {
        DBUtils.execute(conn, "delete from s_shop_cart where id = ?", id);
    }
}
