package com.taoBaby.service.impl;

import com.taoBaby.dao.ProductDao;
import com.taoBaby.dao.ProductTypeDao;
import com.taoBaby.dao.impl.ProductDaoImpl;
import com.taoBaby.dao.impl.ProductTypeDaoImpl;
import com.taoBaby.pojo.Page;
import com.taoBaby.pojo.Product;
import com.taoBaby.pojo.ProductType;
import com.taoBaby.service.ProductService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

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
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        Integer countAll = productDao.countAll();
        List<Product> productList = productDao.getProductList(page, size);
        return new Page<>(page, size, countAll, productList);
    }

    @Override
    public Page<Product> getProductPage(Integer page, Integer size, String productName, String productType) throws Exception {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        Integer countAll = productDao.countAll(productName, productType);
        List<Product> productList = productDao.getProductList(page, size, productName, productType);
        return new Page<>(page, size, countAll, productList);
    }

    @Override
    public Page<Product> getProductPage(Integer page, Integer size, String productName) throws Exception {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        Integer countAll = productDao.countAll(productName);
        List<Product> productList = productDao.getProductList(page, size, productName);
        return new Page<>(page, size, countAll, productList);
    }



    @Override
    public Page<Product> getProductPage(String productType, Integer page, Integer size) throws Exception {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        Integer countAll = productDao.countAll("",productType);
        List<Product> productList = productDao.getProductList(productType, page, size);
        return new Page<>(page, size, countAll, productList);
    }

    @Override
    public Product getProductById(String id) throws Exception {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        Product product = productDao.getProductById(id);
        DBUtils.close(conn);
        return product;
    }

    @Override
    public String addProduct(Product product) throws SQLException {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        productDao.addProduct(product);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteProduct(String id) throws SQLException {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        productDao.deleteProduct(id);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteSelectProduct(String[] ids) throws SQLException {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        productDao.deleteSelectProduct(ids);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String updateProduct(Product product) throws SQLException {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        productDao.updateProduct(product);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public List<Product> getNewProducts(Integer num) throws Exception {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        List<Product> newProducts = productDao.getNewProducts(num);
        DBUtils.close(conn);
        return newProducts;
    }

    @Override
    public List<Product> getProductsByRank() throws Exception {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        List<Product> newProducts = productDao.getProductsByRank();
        DBUtils.close(conn);
        return newProducts;
    }

    @Override
    public List<Product> getProductsById(String typeName, Integer num) throws Exception {
        Connection conn = DBUtils.getConn();
        productDao = SpringUtils.getBean(ProductDao.class);
        ProductTypeDao productTypeDao = new ProductTypeDaoImpl(conn);
        ProductType productType = productTypeDao.getProductType(typeName);
        List<Product> productsByType = productDao.getProductsByType(productType.getId(), num);
        DBUtils.close(conn);
        return productsByType;
    }

}
