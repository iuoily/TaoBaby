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
        ResultSet resultSet = JdbcUtils.executeQuery(conn, "select count(id) id from s_brand");
        resultSet.next();
        return resultSet.getInt("id");
    }

    @Override
    public List<Brand> getBrandList(Integer page, Integer size) throws Exception {
        return JdbcUtils.getBeanList(conn, Brand.class, "select s_brand.id, brand_name, product_type_name brand_type, brand_img from s_brand\n" +
                "left join s_product_type\n" +
                "on s_brand.brand_type = s_product_type.id limit ?,?", (page-1)*size, size);
    }

    @Override
    public Brand getBrand(String brandName) throws Exception {
        return JdbcUtils.getBean(conn, Brand.class, "select * from s_brand where brand_name = ?", brandName);
    }

    @Override
    public List<Brand> getBrands() throws Exception {
        return JdbcUtils.getBeanList(conn, Brand.class, "select * from s_brand");
    }

    @Override
    public List<Brand> listBrandByBrandType(String brandType) throws Exception {
        return JdbcUtils.getBeanList(conn, Brand.class, "select * from s_brand where brand_type = ?", brandType);
    }

    @Override
    public Brand getBrandById(String id) throws Exception {
        return JdbcUtils.getBean(conn, Brand.class, "select * from s_brand where id = ?", id);
    }

    @Override
    public void addBrand(Brand brand) throws SQLException {
        JdbcUtils.execute(conn, "insert  into `s_brand`(`id`,`brand_name`,`brand_type`,`brand_img`) values (?,?,?,?)", brand.getId(), brand.getBrandName(), brand.getBrandType(), brand.getBrandImg());
    }

    @Override
    public void deleteBrand(String id) throws SQLException {
        JdbcUtils.execute(conn, "delete from s_brand where id = ?", id);
    }

    @Override
    public void deleteSelectBrand(String[] ids) throws SQLException {
        StringBuilder sql = new StringBuilder("delete from s_brand where id in (");
        for (String id : ids) {
            sql.append("?,");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(")");
        JdbcUtils.execute(conn, sql.toString(), ids);
    }

    @Override
    public void updateBrand(Brand brand) throws SQLException {
        JdbcUtils.execute(conn,"update s_brand set `brand_name` = ?,`brand_type` = ?,`brand_img` = ? where id = ?", brand.getBrandName(), brand.getBrandType(), brand.getBrandImg(), brand.getId());
    }
}
