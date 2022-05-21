package com.taobaby.service.impl;

import com.taobaby.dao.ShopCartProductDao;
import com.taobaby.dao.impl.ProductDaoImpl;
import com.taobaby.dao.impl.ShopCartProductDaoImpl;
import com.taobaby.pojo.ShopCartProduct;
import com.taobaby.service.ShopCartProductService;
import com.taobaby.utils.DBUtils;

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
        shopCartProductDao = new ShopCartProductDaoImpl(conn);
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
    public String addShopCartProduct(ShopCartProduct shopCartProduct) throws SQLException {
        Connection conn = DBUtils.getConn();
        shopCartProductDao = new ShopCartProductDaoImpl(conn);
        shopCartProductDao.insertShopCartProduct(shopCartProduct);
        DBUtils.close(conn);
        return null;
    }
}
