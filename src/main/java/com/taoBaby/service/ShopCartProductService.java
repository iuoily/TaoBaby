package com.taoBaby.service;

import com.taoBaby.pojo.ShopCartProduct;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface ShopCartProductService {

    /**
     * 根据购物车id查询购物车
     * @param cartId 购物车id
     * @return 购物车
     */
    List<ShopCartProduct> listShopCartProduct(String cartId) throws Exception;

    /**
     * 根据id获取购物车商品id
     * @param productId 商品id
     * @return 购物车商品
     */
    ShopCartProduct getShopCartProduct(String productId, String shopCartId) throws Exception;

    /**
     * 添加到购物车
     * @param shopCartProduct 购物车商品
     */
    void addShopCartProduct(ShopCartProduct shopCartProduct) throws SQLException;

    /**
     * 根据购物车商品id删除购物车商品
     * @param shopCartProductId id
     */
    void removeShopCartProduct(String shopCartProductId) throws SQLException;
}
