package com.taoBaby.service;

import com.taoBaby.pojo.CarouselFigure;
import com.taoBaby.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public interface CarouselfigureService {

    /**
     * 分页获取分页后的轮播图数据
     * @param page 当前页数
     * @param size 每页条数
     * @return
     */
    Page<CarouselFigure> getCarouselfigurePage(Integer page,Integer size) throws Exception;

    /**
     * 根据id获取轮播图
     * @param id
     * @return
     */
    CarouselFigure getCarouselFigure(String id) throws Exception;

    /**
     * 添加轮播图
     * @param carouselFigure
     */
    String addCarouselFigure(CarouselFigure carouselFigure) throws Exception;

    /**
     * 根据id删除轮播图
     * @param id
     */
    String deleteCarouselFigure(String id) throws SQLException;

    /**
     * 删除选择的轮播图
     * @param ids
     */
    String deleteSelectCarouselFigure(String[] ids) throws SQLException;

    /**
     * 更新轮播图
     * @param carouselFigure
     */
    String updateCarouselFigure(CarouselFigure carouselFigure) throws SQLException;

    /**
     * ***********************************************************************************
     * 前台
     */

    /**
     * 查询轮播图数据
     * @return 数据
     */
    List<CarouselFigure> getCarouselfigures() throws Exception;
}
