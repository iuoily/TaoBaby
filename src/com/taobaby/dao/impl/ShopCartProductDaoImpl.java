package com.taobaby.dao.impl;

import com.taobaby.dao.ShopCartProductDao;
import com.taobaby.pojo.ShopCartProduct;
import com.taobaby.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly create on 2022/5/22
 */
public class ShopCartProductDaoImpl implements ShopCartProductDao {

    Connection conn;

    public ShopCartProductDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<ShopCartProduct> listShopCartProduct(String cartId) throws Exception {
        return DBUtils.getBeanList(conn, ShopCartProduct.class, "select * from s_shop_cart_product where shop_cart_id = ?", cartId);
    }

    @Override
    public void insertShopCartProduct(ShopCartProduct shopCartProduct) throws SQLException {
        DBUtils.execute(conn, "insert into s_shop_cart_product(id,shop_cart_id,product_id,product_num) values(?,?,?,?)", shopCartProduct.getId(), shopCartProduct.getShopCartId(), shopCartProduct.getProductId(), shopCartProduct.getProductNum());
    }

    @Override
    public void deleteShopCartProduct(String cartId) throws SQLException {
        DBUtils.execute(conn, "delete from s_shop_cart_product where shop_cart_id = ?", cartId);
    }
}
