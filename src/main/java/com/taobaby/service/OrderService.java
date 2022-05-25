package com.taobaby.service;

import com.taobaby.pojo.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface OrderService {

    /**
     * 根据用户id获取订单
     * @param userId 用户id
     * @return 订单列表
     */
    List<Order> listOrder(String userId) throws Exception;

    /**
     * 根据订单id删除订单
     * @param orderId
     * @return
     */
    String removeOrder(String orderId);

    /**
     * 添加order
     * @param order 订单
     * @return
     */
    String addOrder(Order order) throws SQLException;
}
