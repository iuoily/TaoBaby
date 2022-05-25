package com.taobaby.dao;

import com.taobaby.pojo.ProductType;

import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/12.
 */
public interface ProductTypeDao {

    /**
     * 查询数据表总条数
     * @return 总条数
     * @throws SQLException sql
     */
    Integer countAll() throws SQLException;

    /**
     * 分页查询商品类型
     * @param pageNum 页数
     * @param pageSize 每页行数
     * @return 商品类型列表
     */
    List<ProductType> getProductTypeList(Integer pageNum, Integer pageSize) throws Exception;

    /**
     * 添加商品类型
     * @param productType 类型
     * @exception SQLException sql
     */
    void addProductType(ProductType productType) throws SQLException;

    /**
     * 修改商品类型
     * @param productType 类型
     * @exception SQLException sql
     */
    void updateProductType(ProductType productType) throws SQLException;

    /**
     * 根据商品类型名称获取商品类型
     * @return 集合
     */
    List<ProductType> getProductTypeList() throws Exception;

    /**
     * 根据商品类型名称获取商品类型
     * @param productTypeName 类型名称
     * @exception Exception 异常
     * @return 商品类型
     */
    ProductType getProductType(String productTypeName) throws Exception;

    /**
     * 获取所有商品类型
     * @exception Exception 异常
     * @return 商品类型
     */
    List<ProductType> getProductTypes() throws Exception;

    /**
     * 根据商品类型id获取商品类型
     * @param productTypeId 类型id
     * @exception Exception 异常
     * @return 商品类型
     */
    ProductType getProductTypeName(String productTypeId) throws Exception;

    /**
     * 通过productTypeName删除商品类型
     * @param productTypeName productTypeName
     */
    void delProductTypeByName(String productTypeName) throws SQLException;

    /**
     * 通过productTypeName列表多选删除商品类型
     * @param productTypeNameList productTypeName列表
     */
    void delSelectProductTypeByName(String[] productTypeNameList) throws SQLException;
}
