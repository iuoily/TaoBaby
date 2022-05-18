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
     * 获取所有商品类型和名称查询的数据条数
     * @return 条数
     * @throws SQLException
     */
    Integer countAll(String productName) throws SQLException;

    /**
     * 获取所有商品类型数据条数
     * @return 条数
     * @throws SQLException
     */
    Integer countAll(String productName, String productType) throws SQLException;

    /**
     * 分页获取商品数据
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    List<Product> getProductList(Integer page, Integer size) throws Exception;

    /**
     * 分页获取模糊查询商品数据
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    List<Product> getProductList(Integer page, Integer size, String productName, String productType) throws Exception;

    /**
     * 分页获取模糊查询商品数据 商品名称为空
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    List<Product> getProductList(String productType, Integer page, Integer size) throws Exception;

    /**
     * 分页获取模糊查询商品数据 商品类型为空
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    List<Product> getProductList(Integer page, Integer size, String productName) throws Exception;

    /**
     * 获取商品数据
     * @return 商品数据
     */
    Product getProductById(String id) throws Exception;

    /**
     * 根据品牌名称查询商品
     * @param productName
     * @return
     */
    Product getProduct(String productName) throws Exception;

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
