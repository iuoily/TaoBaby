package com.taobaby.dao;

import com.taobaby.pojo.CarouselFigure;

import java.sql.SQLException;
import java.util.List;

public interface CarouselfigureDao {

    /**
     * 查询所有条数
     * @return 所有条数
     */
    Integer countAll() throws SQLException;

    /**
     * 分页查询轮播图数据
     * @param page 当前页码
     * @param size 每页条数
     * @return 分页数据
     */
    List<CarouselFigure> getCarouselfigure(Integer page, Integer size) throws Exception;

    /**
     * 根据id获取轮播图
     * @param id
     * @return
     */
    CarouselFigure getCarouselFigure(String id) throws Exception;

    /**
     * 根据轮播图顺序查询轮播图
     * @param sequence_num
     * @return
     */
    CarouselFigure getCarouselFigure(Integer sequence_num) throws Exception;

    /**
     * 添加轮播图
     * @param carouselFigure
     */
    void addCarouselFigure(CarouselFigure carouselFigure) throws SQLException;

    /**
     * 根据id删除轮播图
     * @param id
     */
    void deleteCarouselFigure(String id) throws SQLException;

    /**
     * 删除选择的轮播图
     * @param ids
     */
    void deleteSelectCarouselFigure(String[] ids) throws SQLException;

    /**
     * 更新轮播图
     * @param carouselFigure
     */
    void updateCarouselFigure(CarouselFigure carouselFigure) throws SQLException;

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
