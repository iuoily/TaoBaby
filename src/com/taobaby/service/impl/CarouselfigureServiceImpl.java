package com.taobaby.service.impl;

import com.taobaby.dao.CarouselfigureDao;
import com.taobaby.dao.impl.CarouselfigureDaoImpl;
import com.taobaby.pojo.CarouselFigure;
import com.taobaby.pojo.Page;
import com.taobaby.service.CarouselfigureService;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily
 */
public class CarouselfigureServiceImpl implements CarouselfigureService {

    private CarouselfigureDao carouselfigureDao = null;

    @Override
    public Page<CarouselFigure> getCarouselfigurePage(Integer page, Integer size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);
        Integer countAll = carouselfigureDao.countAll();
        List<CarouselFigure> carouselFigureList = this.carouselfigureDao.getCarouselfigureDao(page, size);
        return new Page<>(page ,size, countAll, carouselFigureList);
    }
}
