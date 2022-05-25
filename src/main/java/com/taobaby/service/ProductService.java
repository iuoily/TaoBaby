package com.taobaby.service;

import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;

import java.sql.SQLException;
import java.util.List;

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
     * 分页获取模糊查询商品数据
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    Page<Product> getProductPage(Integer page, Integer size, String productName, String productType) throws Exception;

    /**
     * 分页获取模糊查询商品数据 商品名称为空
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    Page<Product> getProductPage(String productType, Integer page, Integer size) throws Exception;

    /**
     * 分页获取模糊查询商品数据 商品类型为空
     * @param page 当前页码
     * @param size 当前页条数
     * @return 分页数据
     * @throws Exception
     */
    Page<Product> getProductPage(Integer page, Integer size, String productName) throws Exception;

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

    /**
     * 按条数获取新品
     * @param num
     * @return
     */
    List<Product> getNewProducts(Integer num) throws Exception;

    /**
     * 获取商品排行榜
     * @return
     */
    List<Product> getProductsByRank() throws Exception;

    /**
     * 按条数获取新品
     * @param typeName
     * @param num
     * @return
     */
    List<Product> getProductsById(String typeName, Integer num) throws Exception;
}
