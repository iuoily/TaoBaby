package com.taobaby.dao.impl;

import com.taobaby.dao.OrderDao;
import com.taobaby.pojo.Order;
import com.taobaby.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly create on 2022/5/21
 */
public class OrderDaoImpl implements OrderDao {

    private Connection conn;

    public OrderDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Order> queryOrdersByUserId(String userId) throws Exception {
        return DBUtils.getBeanList(conn, Order.class, "select * from s_order where user_id = ? order by create_time desc", userId);
    }

    @Override
    public void deleteOrder(String id) throws SQLException {
        DBUtils.execute(conn, "delete from s_order where id = ?", id);
    }

    @Override
    public void insertOrder(Order order) throws SQLException {
        DBUtils.execute(conn, "insert  into s_order(id,create_time,receiving_address,user_id) values (?,?,?,?)", order.getId(), order.getCreateTime(), order.getReceivingAddress(), order.getUserId());
    }

}
