package com.taoBaby.service.impl;

import com.taoBaby.dao.OrderDao;
import com.taoBaby.dao.OrderProductDao;
import com.taoBaby.dao.impl.OrderDaoImpl;
import com.taoBaby.dao.impl.OrderProductDaoImpl;
import com.taoBaby.pojo.Order;
import com.taoBaby.service.OrderService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly create on 2022/5/21
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = null;

    @Override
    public List<Order> listOrder(String userId) throws Exception {
        Connection conn = DBUtils.getConn();
        orderDao = SpringUtils.getBean(OrderDao.class);
        List<Order> orderList = orderDao.queryOrdersByUserId(userId);
        orderList.forEach(a -> {
            try {
                a.setOrderProductList(new OrderProductServiceImpl().listOrderProduct(a.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        DBUtils.close(conn);
        return orderList;
    }

    @Override
    public String removeOrder(String orderId) {
        Connection conn = null;
        try {
            conn = DBUtils.getConn();
            conn.setAutoCommit(false);
            orderDao = SpringUtils.getBean(OrderDao.class);
            OrderProductDao orderProductDao = new OrderProductDaoImpl(conn);
            orderProductDao.deleteOrderProduct(orderId);
            orderDao.deleteOrder(orderId);
            orderDao.deleteOrder(orderId);
            conn.commit();
            conn.setAutoCommit(true);
            return "ok";
        } catch (SQLException throwables) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
            return "异常" + throwables.getMessage();
        } finally {
            DBUtils.close(conn);
        }
    }

    @Override
    public String addOrder(Order order) throws SQLException {
        Connection conn = DBUtils.getConn();
        orderDao = SpringUtils.getBean(OrderDao.class);
        orderDao.insertOrder(order);
        DBUtils.close(conn);
        return order.getId();
    }
}
