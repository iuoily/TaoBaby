package com.taobaby.dao.impl;

import com.taobaby.dao.ProductDao;
import com.taobaby.dao.ProductTypeDao;
import com.taobaby.pojo.Product;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
public class ProductDaoImpl implements ProductDao {

    private Connection conn;

    public ProductDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer countAll() throws SQLException {
        ResultSet resultSet = JdbcUtils.excuteQuery(conn, "select count(id) id from s_product");
        resultSet.next();
        return resultSet.getInt("id");
    }

    @Override
    public List<Product> getProductList(Integer page, Integer size) throws Exception {
        return JdbcUtils.getBeanList(conn, Product.class, "select s_product.id, s_product.product_name, s_product.product_image, s_product.price,\n" +
                "       s_product_type.product_type_name product_type, s_brand.brand_name product_brand, s_product.create_time\n" +
                "from s_product left join s_product_type\n" +
                "on product_type = s_product_type.id left join s_brand\n" +
                "on product_brand = s_brand.id limit ?,?", (page-1)*size, size);
    }
}
