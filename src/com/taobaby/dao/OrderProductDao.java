package com.taobaby.dao;

import com.taobaby.pojo.OrderProduct;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface OrderProductDao {

    /**
     * 根据订单id查询订单商品列表
     * @param orderId 订单id
     * @return 订单商品列表
     */
    List<OrderProduct> queryOrderProduct(String orderId) throws Exception;

    /**
     * 根据商品id查询商品销量
     * @param productId 商品id
     * @return 销量
     */
    int querySalesNum(String productId) throws SQLException;

    /**
     * 根据订单id删除订单商品
     * @param orderId 订单id
     */
    void deleteOrderProduct(String orderId) throws SQLException;

    /**
     * 新增orderProduct
     * @param orderProduct 订单商品
     */
    void insertOrderProduct(OrderProduct orderProduct) throws SQLException;
}
