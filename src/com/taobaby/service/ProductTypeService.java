package com.taobaby.service;

import com.taobaby.pojo.Page;
import com.taobaby.pojo.ProductType;

import java.util.List;

/**
 * @author iuoily on 2022/5/12.
 */
public interface ProductTypeService {

    /**
     * 获取分页商品类型 ProductType
     * @param pageNum 当前页码
     * @param pageSize 页条数
     * @return 分页商品类型
     * @throws Exception 异常
     */
    Page<ProductType> productTypePage(Integer pageNum, Integer pageSize) throws Exception;

    /**
     * 新增商品类型 productType
     * @param productType 商品类型
     * @exception Exception 商品
     */
    String addProductType(ProductType productType) throws Exception;

    /**
     * 删除商品类型
     * @param id 商品id
     * @return 删除结果
     */
    String delProductType(String id) throws Exception;

    /**
     * 删除选中的商品类型
     * @param productTypeNameList 商品类型列表 productTypeNameList
     * @return 删除结果
     */
    String delSelectProductType(String[] productTypeNameList) throws Exception;

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
     * 修改商品类型 productType
     * @param productType 商品类型
     * @exception Exception 商品
     */
    String updateProductType(ProductType productType) throws Exception;
}
