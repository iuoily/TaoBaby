package com.taobaby.service;

import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;

import java.sql.SQLException;

/**
 * @author iuoily on 2022/5/16.
 */
public interface ProductService {

    /**
     * 获取分页数据
     * @param page 分页页码
     * @param size 分页条数
     * @return 分页数据
     */
    Page<Product> getProductPage(Integer page, Integer size) throws Exception;

    /**
     * 获取商品数据
     * @return 商品数据
     */
    Product getProductById(String id) throws Exception;

    /**
     * 添加商品
     * @param product
     */
    String addProduct(Product product) throws SQLException;

    /**
     * 根据id删除商品
     * @param id
     */
    String deleteProduct(String id) throws SQLException;

    /**
     * 删除选择的商品
     * @param ids
     */
    String deleteSelectProduct(String[] ids) throws SQLException;

    /**
     * 更新商品
     * @param product
     */
    String updateProduct(Product product) throws SQLException;
}
