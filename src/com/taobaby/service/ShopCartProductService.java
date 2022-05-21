package com.taobaby.service;

import com.taobaby.pojo.ShopCart;
import com.taobaby.pojo.ShopCartProduct;

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
     * 添加到购物车
     * @param shopCartProduct 购物车商品
     * @return 添加结果
     */
    String addShopCartProduct(ShopCartProduct shopCartProduct) throws SQLException;
}
