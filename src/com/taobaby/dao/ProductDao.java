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
}
