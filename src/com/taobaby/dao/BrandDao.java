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

    /**
     * 根据品牌名称查询品牌
     * @param brandName
     * @return
     */
    Brand getBrand(String brandName) throws Exception;

    /**
     * 根据品牌id查询品牌
     * @param id
     * @return
     */
    Brand getBrandById(String id) throws Exception;

    /**
     * 添加品牌
     * @param brand
     */
    void addBrand(Brand brand) throws SQLException;

    /**
     * 根据id删除品牌
     * @param id
     */
    void deleteBrand(String id) throws SQLException;

    /**
     * 删除选择的品牌
     * @param ids
     */
    void deleteSelectBrand(String[] ids) throws SQLException;

    /**
     * 更新品牌
     * @param brand
     */
    void updateBrand(Brand brand) throws SQLException;
}
