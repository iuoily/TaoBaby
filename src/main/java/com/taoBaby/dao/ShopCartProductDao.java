package com.taoBaby.dao;

import com.taoBaby.pojo.ShopCartProduct;

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
     * 根据id获取购物车商品id
     * @param productId 商品id
     * @param shopCartId 购物车id
     * @return 购物车商品
     */
    ShopCartProduct queryShopCartProduct(String productId, String shopCartId) throws Exception;

    /**
     * 插入购物车
     */
    void insertShopCartProduct(ShopCartProduct shopCartProduct) throws SQLException;

    /**
     * 根据id删除购物车
     * @param id 根据id删除购物车商品
     */
    void deleteShopCartProduct(String id) throws SQLException;
}
