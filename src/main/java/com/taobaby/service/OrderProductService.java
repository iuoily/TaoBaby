package com.taobaby.service;

import com.taobaby.pojo.OrderProduct;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface OrderProductService {

    /**
     * 根据订单编号查询订单商品
     * @param orderId 订单编号
     * @return 订单商品列表
     */
    List<OrderProduct> listOrderProduct(String orderId) throws Exception;

    /**
     * 根据商品id查询商品销量
     * @param productId 商品id
     * @return 销量
     */
    int getSalesNum(String productId) throws SQLException;

    /**
     * 新增订单商品
     * @param orderProduct 订单商品
     */
    void addOrderProduct(OrderProduct orderProduct) throws SQLException;
}
