package com.taobaby.service;

import com.taobaby.pojo.Page;
import com.taobaby.pojo.ProductType;

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
}
