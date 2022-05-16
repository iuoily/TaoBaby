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
    List<CarouselFigure> getCarouselfigureDao(Integer page, Integer size) throws Exception;
}
