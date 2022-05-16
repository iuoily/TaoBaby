package com.taobaby.service.impl;

import com.taobaby.dao.BrandDao;
import com.taobaby.dao.impl.BrandDaoImpl;
import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Page;
import com.taobaby.service.BrandSerivce;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/16.
 */
public class BrandSerivceImpl implements BrandSerivce {

    private BrandDao brandDao = null;

    @Override
    public Page<Brand> getBrandPage(Integer page, Integer size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Integer countAll = brandDao.countAll();
        List<Brand> brandList = brandDao.getBrandList(page, size);
        JdbcUtils.close(conn);
        return new Page<>(page, size, countAll, brandList);
    }
}
