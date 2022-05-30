package com.taoBaby.service.impl;

import com.taoBaby.dao.BrandDao;
import com.taoBaby.dao.impl.BrandDaoImpl;
import com.taoBaby.pojo.Brand;
import com.taoBaby.pojo.Page;
import com.taoBaby.service.BrandSerivce;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

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
        brandDao = SpringUtils.getBean(BrandDao.class);
        Integer countAll = brandDao.countAll();
        List<Brand> brandList = brandDao.getBrandList(page, size);
        DBUtils.close(conn);
        return new Page<>(page, size, countAll, brandList);
    }

    @Override
    public Brand getBrand(String brandName) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = SpringUtils.getBean(BrandDao.class);
        Brand brand = brandDao.getBrand(brandName);
        DBUtils.close(conn);
        return brand;
    }

    @Override
    public List<Brand> getBrands() throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = SpringUtils.getBean(BrandDao.class);
        List<Brand> brands = brandDao.getBrands();
        DBUtils.close(conn);
        return brands;
    }

    @Override
    public List<Brand> getBrandByProductType(String productTyp) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = SpringUtils.getBean(BrandDao.class);
        List<Brand> brands = brandDao.listBrandByBrandType(productTyp);
        /*List<String> list = brands.stream().map(brand -> brand.getBrandName()).collect(Collectors.toList());*/
        DBUtils.close(conn);
        return brands;
    }

    @Override
    public Brand getBrandById(String id) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = SpringUtils.getBean(BrandDao.class);
        Brand brandById = brandDao.getBrandById(id);
        DBUtils.close(conn);
        return brandById;
    }

    @Override
    public String addBrand(Brand brand) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = SpringUtils.getBean(BrandDao.class);
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
        brandDao = SpringUtils.getBean(BrandDao.class);
        brandDao.deleteBrand(id);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteSelectBrand(String[] ids) throws SQLException {
        Connection conn = DBUtils.getConn();
        brandDao = SpringUtils.getBean(BrandDao.class);
        brandDao.deleteSelectBrand(ids);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String updateBrand(Brand brand) throws Exception {
        Connection conn = DBUtils.getConn();
        brandDao = SpringUtils.getBean(BrandDao.class);
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
