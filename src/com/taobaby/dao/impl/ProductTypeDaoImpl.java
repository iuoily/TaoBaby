package com.taobaby.dao.impl;

import com.taobaby.dao.ProductTypeDao;
import com.taobaby.pojo.ProductType;
import com.taobaby.utils.JdbcUtils;
import jdk.nashorn.internal.scripts.JD;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/12.
 */
public class ProductTypeDaoImpl implements ProductTypeDao {

    private final Connection conn;

    public ProductTypeDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer countAll() throws SQLException {
        ResultSet result = JdbcUtils.excuteQuery(conn, "select count(id) count from s_product_type");
        result.next();
        int row = result.getInt(1);
        return row;
    }

    @Override
    public List<ProductType> getProductTypeList(Integer pageNum, Integer pageSize) throws Exception {
        List<ProductType> beanList = JdbcUtils.getBeanList(conn, ProductType.class, "select * from s_product_type limit ?,?", (pageNum - 1) * pageSize, pageSize);
        return beanList;
    }

    @Override
    public void addProductType(ProductType productType) throws SQLException {
        JdbcUtils.excute(conn,"insert into s_product_type values(?,?,?,?)",
                productType.getId(), productType.getProductTypeName(), productType.getProductTypeDesc(), productType.getProductTypeIcon());
    }

    @Override
    public void updateProductType(ProductType productType) throws SQLException {
        JdbcUtils.excute(conn,"update s_product_type set product_type_name = ?, product_type_desc = ?, product_type_icon = ? where id = ?",
                productType.getProductTypeName(), productType.getProductTypeDesc(), productType.getProductTypeIcon(), productType.getId());
    }

    @Override
    public List<ProductType> getProductTypeList() throws Exception {
        return JdbcUtils.getBeanList(conn, ProductType.class, "select * from s_product_type");
    }

    @Override
    public ProductType getProductType(String productTypeName) throws Exception {
        return JdbcUtils.getBean(conn, ProductType.class, "select * from s_product_type where product_type_name = ?", productTypeName);
    }

    @Override
    public void delProductTypeById(String productTypeName) throws SQLException {
        JdbcUtils.excute(conn,"delete from s_product_type where product_type_name = ?", productTypeName);
    }

}
