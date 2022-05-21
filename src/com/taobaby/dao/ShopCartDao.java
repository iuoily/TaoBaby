package com.taobaby.dao;

import com.taobaby.pojo.ShopCart;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface ShopCartDao {

    /**
     * 根据用户id查询购物车
     * @param userId 用户id
     * @return 购物车
     */
    ShopCart queryShopCart(String userId) throws Exception;

    /**
     * 添加购物车
     */
    void insertShopCart(ShopCart shopCart) throws SQLException;

    /**
     * 根据id删除购物车
     * @param id 购物车id
     */
    void deleteShopCart(String id) throws SQLException;
}
