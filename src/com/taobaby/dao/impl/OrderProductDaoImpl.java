package com.taobaby.dao.impl;

import com.taobaby.dao.OrderProductDao;
import com.taobaby.pojo.OrderProduct;
import com.taobaby.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly create on 2022/5/21
 */
public class OrderProductDaoImpl implements OrderProductDao {

    private Connection conn;

    public OrderProductDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<OrderProduct> queryOrderProduct(String orderId) throws Exception {
        return DBUtils.getBeanList(conn, OrderProduct.class, "select * from s_order_product where order_id = ?", orderId);
    }

    @Override
    public int querySalesNum(String productId) throws SQLException {
        ResultSet resultSet = DBUtils.executeQuery(conn, "select sum(product_num) num from s_order_product where product_id = ? group by product_id", productId);
        int num = 0;
        if (resultSet.next()) {
            num = resultSet.getInt("num");
        }

        return num;
    }

    @Override
    public void deleteOrderProduct(String orderId) throws SQLException {
        DBUtils.execute(conn, "delete from s_order_product where order_id = ?", orderId);
    }

    @Override
    public void insertOrderProduct(OrderProduct orderProduct) throws SQLException {
        DBUtils.execute(conn, "insert  into s_order_product(id,order_id,product_id,product_num) values (?,?,?,?)", orderProduct.getId(), orderProduct.getOrderId(), orderProduct.getProductId(), orderProduct.getProductNum());
    }
}
