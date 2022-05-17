package com.taobaby.service;

import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
public interface BrandSerivce {

    /**
     * 分页获取品牌
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    Page<Brand> getBrandPage(Integer page, Integer size) throws Exception;

    /**
     * 根据品牌名称查询品牌
     * @param brandName
     * @return
     */
    Brand getBrand(String brandName) throws Exception;

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> getBrands() throws Exception;

    /**
     * 根据商品类型查询所有品牌
     * @return
     */
    List<Brand> getBrandByProductType(String ProductType) throws Exception;

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
    String addBrand(Brand brand) throws Exception;

    /**
     * 根据id删除品牌
     * @param id
     */
    String deleteBrand(String id) throws SQLException;

    /**
     * 删除选择的品牌
     * @param ids
     */
    String deleteSelectBrand(String[] ids) throws SQLException;

    /**
     * 更新品牌
     * @param brand
     */
    String updateBrand(Brand brand) throws SQLException;
}