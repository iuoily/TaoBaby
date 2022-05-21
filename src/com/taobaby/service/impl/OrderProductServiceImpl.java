package com.taobaby.service.impl;

import com.taobaby.dao.OrderProductDao;
import com.taobaby.dao.impl.OrderProductDaoImpl;
import com.taobaby.pojo.OrderProduct;
import com.taobaby.service.OrderProductService;
import com.taobaby.utils.DBUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author iuoly create on 2022/5/21
 */
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductDao orderProductDao = null;

    @Override
    public List<OrderProduct> listOrderProduct(String orderId) throws Exception {
        Connection conn = DBUtils.getConn();
        orderProductDao = new OrderProductDaoImpl(conn);
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
}
