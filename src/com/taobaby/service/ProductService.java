package com.taobaby.service;

import com.taobaby.pojo.Page;
import com.taobaby.pojo.Product;

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
}
