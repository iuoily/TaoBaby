package com.taobaby.service.impl;

import com.taobaby.dao.ProductTypeDao;
import com.taobaby.dao.impl.ProductTypeDaoImpl;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.ProductType;
import com.taobaby.service.ProductTypeService;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author iuoily on 2022/5/12.
 */
public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeDao productTypeDao = null;

    @Override
    public Page<ProductType> productTypePage(Integer pageNum, Integer pageSize) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        List<ProductType> productTypeList = productTypeDao.getProductTypeList(pageNum, pageSize);
        Integer pageNumTotal = productTypeDao.countAll();
        JdbcUtils.close(conn);
        return new Page<ProductType>(pageNum, pageSize, pageNumTotal, productTypeList);
    }

    @Override
    public String addProductType(ProductType productType) throws Exception {
        if (isExist(productType)) {
            return "该分类已存在";
        }
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        productTypeDao.addProductType(productType);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String delProductType(String productTypeName) throws Exception {
        ProductType p = new ProductType("", productTypeName, "", "");
        if (!isExist(p)) {
            return "该分类不存在";
        }
        Connection conn = JdbcUtils.getConn();
        ProductTypeDaoImpl productTypeDao = new ProductTypeDaoImpl(conn);
        productTypeDao.delProductTypeById(productTypeName);
        return null;
    }

    private Boolean isExist(ProductType productType) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        ProductType productT = productTypeDao.getProductType(productType.getProductTypeName());
        JdbcUtils.close(conn);
        if (productT == null) {
            return false;
        }
        return productT.equals(productType);
    }

}
