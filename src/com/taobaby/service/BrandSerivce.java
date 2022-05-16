package com.taobaby.service;

import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Page;

import java.sql.SQLException;


/**
 * @author iuoily on 2022/5/16.
 */
public interface BrandSerivce {

    Page<Brand> getBrandPage(Integer page, Integer size) throws Exception;
}