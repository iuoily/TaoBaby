package com.taobaby.service.impl;

import com.taobaby.dao.ProductDao;
import com.taobaby.dao.impl.ProductDaoImpl;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;
import com.taobaby.service.ProductService;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = null;

    @Override
    public Page<Product> getProductPage(Integer page, Integer size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productDao = new ProductDaoImpl(conn);
        Integer countAll = productDao.countAll();
        List<Product> productList = productDao.getProductList(page, size);
        return new Page<>(page, size, countAll, productList);
    }

    @Override
    public Product getProductById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productDao = new ProductDaoImpl(conn);
        Product product = productDao.getProductById(id);
        JdbcUtils.close(conn);
        return product;
    }

    @Override
    public String addProduct(Product product) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        productDao = new ProductDaoImpl(conn);
        productDao.addProduct(product);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String deleteProduct(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        productDao = new ProductDaoImpl(conn);
        productDao.deleteProduct(id);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String deleteSelectProduct(String[] ids) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        productDao = new ProductDaoImpl(conn);
        productDao.deleteSelectProduct(ids);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String updateProduct(Product product) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        productDao = new ProductDaoImpl(conn);
        productDao.updateProduct(product);
        JdbcUtils.close(conn);
        return null;
    }

}
