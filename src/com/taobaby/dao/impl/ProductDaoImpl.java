package com.taobaby.dao.impl;

import com.taobaby.dao.ProductDao;
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
        return JdbcUtils.getBeanList(conn, Product.class, "select s_product.id, s_product.product_name" +
                ", s_product.product_image, s_product.price, s_product_type.product_type_name product_type" +
                ", s_brand.brand_name product_brand, s_product.create_time from s_product left join " +
                "s_product_type on product_type = s_product_type.id left join s_brand\n" +
                "on product_brand = s_brand.id limit ?,?", (page-1)*size, size);
    }

    @Override
    public List<Product> getProductList(Integer page, Integer size, String productName, String productType) throws Exception {
        return JdbcUtils.getBeanList(conn, Product.class, "select s_product.id, s_product.product_name \n" +
                "                , s_product.product_image, s_product.price, s_product_type.product_type_name product_type \n" +
                "                , s_brand.brand_name product_brand, s_product.create_time from s_product left join  \n" +
                "                s_product_type on product_type = s_product_type.id left join s_brand \n" +
                "                on product_brand = s_brand.id\n" +
                "where s_product.product_type = '?' and s_product.product_name like '%?%' limit ?,?", productName, productType, (page-1)*size, size);
    }

    @Override
    public List<Product> getProductList(String productType, Integer page, Integer size) throws Exception {
        return JdbcUtils.getBeanList(conn, Product.class, "select s_product.id, s_product.product_name \n" +
                "                , s_product.product_image, s_product.price, s_product_type.product_type_name product_type \n" +
                "                , s_brand.brand_name product_brand, s_product.create_time from s_product left join  \n" +
                "                s_product_type on product_type = s_product_type.id left join s_brand \n" +
                "                on product_brand = s_brand.id\n" +
                "where s_product.product_type = ? limit ?,?", productType, (page-1)*size, size);
    }

    @Override
    public List<Product> getProductList(Integer page, Integer size, String productName) throws Exception {
        return JdbcUtils.getBeanList(conn, Product.class, "select s_product.id, s_product.product_name \n" +
                "                , s_product.product_image, s_product.price, s_product_type.product_type_name product_type \n" +
                "                , s_brand.brand_name product_brand, s_product.create_time from s_product left join  \n" +
                "                s_product_type on product_type = s_product_type.id left join s_brand \n" +
                "                on product_brand = s_brand.id\n" +
                "where s_product.product_name like ? limit ?,?", "%"+productName+"%", (page-1)*size, size);
    }

    @Override
    public Product getProductById(String id) throws Exception {
        return JdbcUtils.getBean(conn, Product.class, "select * from s_product where id = ?", id);
    }

    @Override
    public Product getProduct(String productName) throws Exception {
        return JdbcUtils.getBean(conn, Product.class, "select * from s_product where product_name = ?", productName);
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        JdbcUtils.excute(conn, "insert  into s_product(id,product_name,product_image,price," +
                "product_type,product_desc,create_time,product_brand) values (?,?,?,?,?,?,?,?)"
                , product.getId(), product.getProductName(), product.getProductImage(), product.getPrice()
                , product.getProductType(), product.getProductDesc(), product.getCreateTime(), product.getProductBrand());
    }

    @Override
    public void deleteProduct(String id) throws SQLException {
        JdbcUtils.excute(conn, "delete from s_product where id = ?", id);
    }

    @Override
    public void deleteSelectProduct(String[] ids) throws SQLException {
        StringBuilder sql = new StringBuilder("delete from s_product where id in (");
        for (String id : ids) {
            sql.append("?,");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(")");
        JdbcUtils.excute(conn, sql.toString(), ids);
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        JdbcUtils.excute(conn, "update s_product set product_name = ?,product_image = ?,price = ?," +
                        "product_type = ? ,product_desc = ?, product_brand = ? where id = ?"
                , product.getProductName(), product.getProductImage(), product.getPrice(), product.getProductType()
                , product.getProductDesc(), product.getProductBrand(), product.getId());
    }
}
