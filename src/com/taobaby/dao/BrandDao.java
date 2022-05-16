package com.taobaby.dao;

import com.taobaby.pojo.Brand;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
public interface BrandDao {

    /**
     *  获取所有条数
     * @return 所有条数
     */
    Integer countAll() throws SQLException;

    /**
     * 分页获取品牌列表
     * @param page 当前页码
     * @param size 当页条数
     * @return 分页数据
     */
    List<Brand> getBrandList(Integer page, Integer size) throws Exception;
}
