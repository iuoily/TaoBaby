package com.taobaby.service;

import com.taobaby.pojo.OrderProduct;

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
}
