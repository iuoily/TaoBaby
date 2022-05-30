package com.taoBaby.dao;

import com.taoBaby.pojo.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly
 */
public interface OrderDao {

    /**
     * 根据用户id查询订单
     * @param userId 用户id
     * @return 订单
     */
    List<Order> queryOrdersByUserId(String userId) throws Exception;

    /**
     * 根据订单id删除订单
     * @param id 订单id
     */
    void deleteOrder(String id) throws SQLException;

    /**
     * 新增订单
     * @param order 订单
     */
    void insertOrder(Order order) throws SQLException;
}
