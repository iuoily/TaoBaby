package com.taobaby.service;

import com.taobaby.pojo.CarouselFigure;
import com.taobaby.pojo.Page;

import java.sql.SQLException;

public interface CarouselfigureService {

    /**
     * 分页获取分页后的轮播图数据
     * @param page 当前页数
     * @param size 每页条数
     * @return
     */
    Page<CarouselFigure> getCarouselfigurePage(Integer page,Integer size) throws Exception;
}
