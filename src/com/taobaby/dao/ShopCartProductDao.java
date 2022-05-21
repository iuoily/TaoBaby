package com.taobaby.dao;

import com.taobaby.pojo.ShopCartProduct;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface ShopCartProductDao {

    /**
     * 根据购物车id查询购物车
     * @param cartId 购物车id
     * @return 购物车
     */
    List<ShopCartProduct> listShopCartProduct(String cartId) throws Exception;

    /**
     * 插入购物车
     */
    void insertShopCartProduct(ShopCartProduct shopCartProduct) throws SQLException;

    /**
     * 根据id删除购物车
     * @param cartId 根据购物车id删除购物车商品
     */
    void deleteShopCartProduct(String cartId) throws SQLException;
}
