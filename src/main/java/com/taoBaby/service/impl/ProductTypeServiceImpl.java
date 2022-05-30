package com.taoBaby.service.impl;

import com.taoBaby.dao.ProductTypeDao;
import com.taoBaby.dao.impl.ProductTypeDaoImpl;
import com.taoBaby.pojo.Page;
import com.taoBaby.pojo.ProductType;
import com.taoBaby.service.ProductTypeService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author iuoily on 2022/5/12.
 */
public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeDao productTypeDao = null;

    @Override
    public Page<ProductType> productTypePage(Integer pageNum, Integer pageSize) throws Exception {
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        List<ProductType> productTypeList = productTypeDao.getProductTypeList(pageNum, pageSize);
        Integer pageNumTotal = productTypeDao.countAll();
        DBUtils.close(conn);
        return new Page<ProductType>(pageNum, pageSize, pageNumTotal, productTypeList);
    }

    @Override
    public String addProductType(ProductType productType) throws Exception {
        if (isExist(productType)) {
            return "该分类已存在";
        }
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        productTypeDao.addProductType(productType);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String delProductType(String productTypeName) throws Exception {
        Connection conn = DBUtils.getConn();
        ProductType p = new ProductType("", productTypeName, "", "");
        if (!isExist(p)) {
            return "该数据不存在";
        }
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        productTypeDao.delProductTypeByName(productTypeName);
        DBUtils.close(conn);
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
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        productTypeDao.delSelectProductTypeByName(productTypeNameList);
        DBUtils.close(conn);
        return null;
    }


    @Override
    public String updateProductType(ProductType productType) throws Exception {
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        ProductType productT = productTypeDao.getProductType(productType.getProductTypeName());
        if (productT != null) {
            if (!productT.getId().equals(productType.getId())) {
                return "该分类名称已存在";
            }
        }
        productTypeDao.updateProductType(productType);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public ProductType getProductType(String productTypeName) throws Exception {
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        ProductType productType = productTypeDao.getProductType(productTypeName);
        DBUtils.close(conn);
        return productType;
    }

    @Override
    public List<ProductType> getProductTypes() throws Exception {
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        List<ProductType> productTypes = productTypeDao.getProductTypes();
        DBUtils.close(conn);
        return productTypes;
    }

    @Override
    public ProductType getProductTypeName(String productTypeId) throws Exception {
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        ProductType productType = productTypeDao.getProductTypeName(productTypeId);
        DBUtils.close(conn);
        return productType;
    }

    /**
     * 判断商品名称是否已存在。
     * @param productType 商品类型
     * @return true 类型名称已存在
     * @throws Exception sql
     */
    private Boolean isExist(ProductType productType) throws Exception {
        Connection conn = DBUtils.getConn();
        productTypeDao = SpringUtils.getBean(ProductTypeDao.class);
        ProductType productT = productTypeDao.getProductType(productType.getProductTypeName());
        DBUtils.close(conn);
        if (productT == null) {
            return false;
        }
        return productT.getProductTypeName().equals(productType.getProductTypeName());
    }

}
