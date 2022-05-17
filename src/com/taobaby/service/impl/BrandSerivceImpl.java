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

    @Override
    public Brand getBrand(String brandName) throws Exception {
        Connection conn = JdbcUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Brand brand = brandDao.getBrand(brandName);
        JdbcUtils.close(conn);
        return brand;
    }

    @Override
    public Brand getBrandById(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Brand brandById = brandDao.getBrandById(id);
        JdbcUtils.close(conn);
        return brandById;
    }

    @Override
    public String addBrand(Brand brand) throws Exception {
        Connection conn = JdbcUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Brand b = brandDao.getBrand(brand.getBrandName());
        if (null != b) {
            return "品牌名称重复！";
        }
        brandDao.addBrand(brand);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String deleteBrand(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        brandDao.deleteBrand(id);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String deleteSelectBrand(String[] ids) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        brandDao.deleteSelectBrand(ids);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String updateBrand(Brand brand) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        brandDao.updateBrand(brand);
        JdbcUtils.close(conn);
        return null;
    }
}
