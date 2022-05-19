package com.taobaby.web.front;

import com.taobaby.common.BaseServlet;
import com.taobaby.pojo.CarouselFigure;
import com.taobaby.pojo.Product;
import com.taobaby.pojo.ProductType;
import com.taobaby.pojo.SearchHistory;
import com.taobaby.service.CarouselfigureService;
import com.taobaby.service.ProductService;
import com.taobaby.service.ProductTypeService;
import com.taobaby.service.SearchHistorysService;
import com.taobaby.service.impl.CarouselfigureServiceImpl;
import com.taobaby.service.impl.ProductServiceImpl;
import com.taobaby.service.impl.ProductTypeServiceImpl;
import com.taobaby.service.impl.SearchHistorysServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author iuoily on 2022/5/19.
 */
@WebServlet(value = "/front/index", loadOnStartup = 0)
public class IndexServlet extends BaseServlet {

    private ProductTypeService productTypeService = new ProductTypeServiceImpl();
    private CarouselfigureService carouselfigureService = new CarouselfigureServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private SearchHistorysService searchHistorysService = new SearchHistorysServiceImpl();

    public void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 所有商品分类
            List<ProductType> productTypes = productTypeService.getProductTypes();
            // 轮播图
            List<CarouselFigure> carouselfigures = carouselfigureService.getCarouselfigures();
            // 新品展示
            List<Product> newProducts = productService.getNewProducts(6);
            // 排行榜展示
            List<Product> productsByRank = productService.getProductsByRank();
            // 搜索历史
            List<SearchHistory> searchHistorys = searchHistorysService.getAllHistory();

            // 商品瀑布流
            List<Product> list = productService.getProductsById("全球进口", 5);
            List<Product> list1 = productService.getProductsById("服装服饰", 12);
            List<Product> list2 = productService.getProductsById("护肤美妆", 5);
            List<Product> list3 = productService.getProductsById("图书学习", 12);

            req.setAttribute("allProductTypes", productTypes);
            req.setAttribute("allcarouselFigures",carouselfigures);
            req.setAttribute("newProducts",newProducts);
            req.setAttribute("rankings",productsByRank);
            req.setAttribute("searchHistorys",searchHistorys);
            req.setAttribute("list",list);
            req.setAttribute("list1",list1);
            req.setAttribute("list2",list2);
            req.setAttribute("list3",list3);

            forward("/front/index/index.jsp", req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
