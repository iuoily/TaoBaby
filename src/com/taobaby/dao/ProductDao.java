package com.taobaby.dao;

import com.taobaby.pojo.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
public interface ProductDao {

    /**
     * 获取所有数据条数
     * @return 条数
     * @throws SQLException
     */
    Integer countAll() throws SQLException;

    /**
     * 分页获取商品数据
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    List<Product> getProductList(Integer page, Integer size) throws Exception;

    /**
     * 根据品牌名称查询商品
     * @param ProductName
     * @return
     */
    Product getProduct(String ProductName) throws Exception;

    /**
     * 添加商品
     * @param product
     */
    void addProduct(Product product) throws SQLException;

    /**
     * 根据id删除商品
     * @param id
     */
    void deleteProduct(String id) throws SQLException;

    /**
     * 删除选择的商品
     * @param ids
     */
    void deleteSelectProduct(String[] ids) throws SQLException;

    /**
     * 更新商品
     * @param product
     */
    void updateProduct(Product product) throws SQLException;
}
