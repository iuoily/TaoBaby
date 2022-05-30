package com.taoBaby.service.impl;

import com.taoBaby.dao.ShopCartProductDao;
import com.taoBaby.dao.impl.ProductDaoImpl;
import com.taoBaby.dao.impl.ShopCartProductDaoImpl;
import com.taoBaby.pojo.ShopCartProduct;
import com.taoBaby.service.ShopCartProductService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoly create on 2022/5/22
 */
public class ShopCartProductServiceImpl implements ShopCartProductService {

    private ShopCartProductDao shopCartProductDao = null;

    @Override
    public List<ShopCartProduct> listShopCartProduct(String cartId) throws Exception {
        Connection conn = DBUtils.getConn();
        shopCartProductDao = SpringUtils.getBean(ShopCartProductDao.class);
        List<ShopCartProduct> shopCartProducts = shopCartProductDao.listShopCartProduct(cartId);
        shopCartProducts.forEach(a->{
            try {
                a.setProduct(new ProductDaoImpl(conn).getProductById(a.getProductId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        DBUtils.close(conn);
        return shopCartProducts;
    }

    @Override
    public ShopCartProduct getShopCartProduct(String productId, String shopCartId) throws Exception {
        Connection conn = DBUtils.getConn();
        shopCartProductDao = SpringUtils.getBean(ShopCartProductDao.class);
        ShopCartProduct shopCartProduct = shopCartProductDao.queryShopCartProduct(productId, shopCartId);
        DBUtils.close(conn);
        return shopCartProduct;
    }

    @Override
    public void addShopCartProduct(ShopCartProduct shopCartProduct) throws SQLException {
        Connection conn = DBUtils.getConn();
        shopCartProductDao = SpringUtils.getBean(ShopCartProductDao.class);
        shopCartProductDao.insertShopCartProduct(shopCartProduct);
        DBUtils.close(conn);
    }

    @Override
    public void removeShopCartProduct(String shopCartProductId) throws SQLException {
        Connection conn = DBUtils.getConn();
        shopCartProductDao = SpringUtils.getBean(ShopCartProductDao.class);
        shopCartProductDao.deleteShopCartProduct(shopCartProductId);
        DBUtils.close(conn);
    }

}
