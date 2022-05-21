package com.taobaby.service.impl;

import com.taobaby.dao.BrandDao;
import com.taobaby.dao.impl.BrandDaoImpl;
import com.taobaby.pojo.Brand;
import com.taobaby.pojo.Page;
import com.taobaby.service.BrandSerivce;
import com.taobaby.utils.DBUtils;

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
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Integer countAll = brandDao.countAll();
        List<Brand> brandList = brandDao.getBrandList(page, size);
        DBUtils.close(conn);
        return new Page<>(page, size, countAll, brandList);
    }

    @Override
    public Brand getBrand(String brandName) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Brand brand = brandDao.getBrand(brandName);
        DBUtils.close(conn);
        return brand;
    }

    @Override
    public List<Brand> getBrands() throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        List<Brand> brands = brandDao.getBrands();
        DBUtils.close(conn);
        return brands;
    }

    @Override
    public List<Brand> getBrandByProductType(String productTyp) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        List<Brand> brands = brandDao.listBrandByBrandType(productTyp);
        /*List<String> list = brands.stream().map(brand -> brand.getBrandName()).collect(Collectors.toList());*/
        DBUtils.close(conn);
        return brands;
    }

    @Override
    public Brand getBrandById(String id) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Brand brandById = brandDao.getBrandById(id);
        DBUtils.close(conn);
        return brandById;
    }

    @Override
    public String addBrand(Brand brand) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Brand b = brandDao.getBrand(brand.getBrandName());
        if (null != b) {
            return "品牌名称重复！";
        }
        brandDao.addBrand(brand);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteBrand(String id) throws SQLException {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        brandDao.deleteBrand(id);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteSelectBrand(String[] ids) throws SQLException {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        brandDao.deleteSelectBrand(ids);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String updateBrand(Brand brand) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = new BrandDaoImpl(conn);
        Brand old = brandDao.getBrandById(brand.getId());
        Brand brand1 = brandDao.getBrand(brand.getBrandName());
        if (null != brand1 && !old.getId().equals(brand1.getId())) {
            return "该品牌已存在！";
        }
        brandDao.updateBrand(brand);
        DBUtils.close(conn);
        return null;
    }
}
