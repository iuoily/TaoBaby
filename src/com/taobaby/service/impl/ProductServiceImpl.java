package com.taobaby.service.impl;

import com.taobaby.dao.ProductDao;
import com.taobaby.dao.ProductTypeDao;
import com.taobaby.dao.impl.ProductDaoImpl;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;
import com.taobaby.service.ProductService;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
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
}
