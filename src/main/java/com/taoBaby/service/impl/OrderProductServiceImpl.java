package com.taoBaby.service.impl;

import com.taoBaby.dao.OrderProductDao;
import com.taoBaby.dao.impl.OrderProductDaoImpl;
import com.taoBaby.pojo.OrderProduct;
import com.taoBaby.service.OrderProductService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly create on 2022/5/21
 */
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductDao orderProductDao = null;

    @Override
    public List<OrderProduct> listOrderProduct(String orderId) throws Exception {
        Connection conn = DBUtils.getConn();
        orderProductDao = SpringUtils.getBean(OrderProductDao.class);
        List<OrderProduct> list = orderProductDao.queryOrderProduct(orderId);
        list.forEach(a-> {
            try {
                a.setProduct(new ProductServiceImpl().getProductById(a.getProductId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        DBUtils.close(conn);
        return list;
    }

    @Override
    public int getSalesNum(String productId) throws SQLException {
        Connection conn = DBUtils.getConn();
        orderProductDao = SpringUtils.getBean(OrderProductDao.class);
        int i = orderProductDao.querySalesNum(productId);
        DBUtils.close(conn);
        return i;
    }

    @Override
    public void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        Connection conn = DBUtils.getConn();
        orderProductDao = SpringUtils.getBean(OrderProductDao.class);
        orderProductDao.insertOrderProduct(orderProduct);
        DBUtils.close(conn);
    }
}
