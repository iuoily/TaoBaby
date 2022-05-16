package com.taobaby.dao.impl;

import com.taobaby.dao.BrandDao;
import com.taobaby.pojo.Brand;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
public class BrandDaoImpl implements BrandDao {

    private Connection conn;

    public BrandDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer countAll() throws SQLException {
        ResultSet resultSet = JdbcUtils.excuteQuery(conn, "select count(id) id from s_brand");
        resultSet.next();
        return resultSet.getInt("id");
    }

    @Override
    public List<Brand> getBrandList(Integer page, Integer size) throws Exception {
        return JdbcUtils.getBeanList(conn, Brand.class, "select s_brand.id, brand_name, product_type_name brand_type, brand_img from s_brand\n" +
                "left join s_product_type\n" +
                "on s_brand.brand_type = s_product_type.id limit ?,?", (page-1)*size, size);
    }
}
