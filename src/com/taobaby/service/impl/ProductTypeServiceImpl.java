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
        Connection conn = JdbcUtils.getConn();
        ProductType p = new ProductType("", productTypeName, "", "");
        if (!isExist(p)) {
            return "该数据不存在";
        }
        productTypeDao = new ProductTypeDaoImpl(conn);
        productTypeDao.delProductTypeByName(productTypeName);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String delSelectProductType(String[] productTypeNameList) throws Exception {
        for (String s : productTypeNameList) {
            ProductType p = new ProductType("", s, "", "");
            if (!isExist(p)) {
                return s + "数据不存在";
            };
        }
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        productTypeDao.delSelectProductTypeByName(productTypeNameList);
        JdbcUtils.close(conn);
        return null;
    }


    @Override
    public String updateProductType(ProductType productType) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        ProductType productT = productTypeDao.getProductType(productType.getProductTypeName());
        if (productT != null) {
            if (!productT.getId().equals(productType.getId())) {
                return "该分类名称已存在";
            }
        }
        productTypeDao.updateProductType(productType);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public ProductType getProductType(String productTypeName) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        ProductType productType = productTypeDao.getProductType(productTypeName);
        JdbcUtils.close(conn);
        return productType;
    }

    @Override
    public List<ProductType> getProductTypes() throws Exception {
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        List<ProductType> productTypes = productTypeDao.getProductTypes();
        JdbcUtils.close(conn);
        return productTypes;
    }

    @Override
    public ProductType getProductTypeName(String productTypeId) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        ProductType productType = productTypeDao.getProductTypeName(productTypeId);
        JdbcUtils.close(conn);
        return productType;
    }

    /**
     * 判断商品名称是否已存在。
     * @param productType 商品类型
     * @return true 类型名称已存在
     * @throws Exception sql
     */
    private Boolean isExist(ProductType productType) throws Exception {
        Connection conn = JdbcUtils.getConn();
        productTypeDao = new ProductTypeDaoImpl(conn);
        ProductType productT = productTypeDao.getProductType(productType.getProductTypeName());
        JdbcUtils.close(conn);
        if (productT == null) {
            return false;
        }
        return productT.getProductTypeName().equals(productType.getProductTypeName());
    }

}
