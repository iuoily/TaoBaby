package com.taobaby.service.impl;

import com.taobaby.dao.CarouselfigureDao;
import com.taobaby.dao.impl.CarouselfigureDaoImpl;
import com.taobaby.pojo.CarouselFigure;
import com.taobaby.pojo.Page;
import com.taobaby.service.CarouselfigureService;
import com.taobaby.utils.DBUtils;

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
        Connection conn = DBUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);
        Integer countAll = carouselfigureDao.countAll();
        List<CarouselFigure> carouselFigureList = this.carouselfigureDao.getCarouselfigure(page, size);
        DBUtils.close(conn);
        return new Page<>(page ,size, countAll, carouselFigureList);
    }

    @Override
    public CarouselFigure getCarouselFigure(String id) throws Exception {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);
        CarouselFigure carouselFigure = carouselfigureDao.getCarouselFigure(id);
        DBUtils.close(conn);
        return carouselFigure;
    }

    @Override
    public String addCarouselFigure(CarouselFigure carouselFigure) throws Exception {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);

        /* 轮播图顺序重复
        CarouselFigure figure = carouselfigureDao.getCarouselFigure(carouselFigure.getSequenceNum());
        if (null != figure) {
            return "序号重复！";
        }*/
        carouselfigureDao.addCarouselFigure(carouselFigure);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteCarouselFigure(String id) throws SQLException {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);
        carouselfigureDao.deleteCarouselFigure(id);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteSelectCarouselFigure(String[] ids) throws SQLException {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);
        carouselfigureDao.deleteSelectCarouselFigure(ids);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String updateCarouselFigure(CarouselFigure carouselFigure) throws SQLException {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);
        carouselfigureDao.updateCarouselFigure(carouselFigure);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public List<CarouselFigure> getCarouselfigures() throws Exception {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = new CarouselfigureDaoImpl(conn);
        List<CarouselFigure> carouselfigures = carouselfigureDao.getCarouselfigures();
        DBUtils.close(conn);
        return carouselfigures;
    }
}
