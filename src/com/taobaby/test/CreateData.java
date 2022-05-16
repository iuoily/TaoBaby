package com.taobaby.test;

import com.taobaby.dao.ProductTypeDao;
import com.taobaby.dao.impl.ProductTypeDaoImpl;
import com.taobaby.pojo.ProductType;
import com.taobaby.utils.JdbcUtils;
import com.taobaby.utils.UUIDUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author sun
 */
public class CreateData {

    public static void createProduct() throws SQLException {
        Connection conn = JdbcUtils.getConn();
        ProductTypeDao productTypeDao = new ProductTypeDaoImpl(conn);
        for (int i=0; i<500; i++) {
            String id = UUIDUtils.getId() + i;
            String name = "类型名称" + i;
            String desc = "类型描述" + i;
            String icon = "icon-like";
            ProductType productType = new ProductType(id, name, desc, icon);
            productTypeDao.addProductType(productType);
            System.out.println("成功插入 " + i + " 条数据");
        }
    }

    public static void main(String[] args) throws SQLException {
        createProduct();
    }
}
