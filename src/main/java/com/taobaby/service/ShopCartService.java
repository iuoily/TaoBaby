package com.taobaby.service;

import com.taobaby.pojo.ShopCart;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface ShopCartService {

    /**
     * 根据用户id查询购物车
     * @param userId 用户id
     * @return 购物车
     */
    ShopCart getShopCart(String userId) throws Exception;

    /**
     * 根据购物车id删除购物车
     * @param cartId 购物车id
     * @return 删除结果
     */
    String removeShopCart(String cartId);

    /**
     * 添加购物车
     * @param shopCart 购物车id
     * @return 删除结果
     */
    String addShopCart(ShopCart shopCart) throws SQLException;
}
