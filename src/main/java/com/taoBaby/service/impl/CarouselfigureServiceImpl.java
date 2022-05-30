package com.taoBaby.service.impl;

import com.taoBaby.dao.CarouselfigureDao;
import com.taoBaby.dao.impl.CarouselfigureDaoImpl;
import com.taoBaby.pojo.CarouselFigure;
import com.taoBaby.pojo.Page;
import com.taoBaby.service.CarouselfigureService;
import com.taoBaby.utils.DBUtils;
import com.taoBaby.utils.SpringUtils;

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
        carouselfigureDao = SpringUtils.getBean(CarouselfigureDao.class);
        Integer countAll = carouselfigureDao.countAll();
        List<CarouselFigure> carouselFigureList = this.carouselfigureDao.getCarouselfigure(page, size);
        DBUtils.close(conn);
        return new Page<>(page ,size, countAll, carouselFigureList);
    }

    @Override
    public CarouselFigure getCarouselFigure(String id) throws Exception {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = SpringUtils.getBean(CarouselfigureDao.class);
        CarouselFigure carouselFigure = carouselfigureDao.getCarouselFigure(id);
        DBUtils.close(conn);
        return carouselFigure;
    }

    @Override
    public String addCarouselFigure(CarouselFigure carouselFigure) throws Exception {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = SpringUtils.getBean(CarouselfigureDao.class);

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
        carouselfigureDao = SpringUtils.getBean(CarouselfigureDao.class);
        carouselfigureDao.deleteCarouselFigure(id);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteSelectCarouselFigure(String[] ids) throws SQLException {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = SpringUtils.getBean(CarouselfigureDao.class);
        carouselfigureDao.deleteSelectCarouselFigure(ids);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String updateCarouselFigure(CarouselFigure carouselFigure) throws SQLException {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = SpringUtils.getBean(CarouselfigureDao.class);
        carouselfigureDao.updateCarouselFigure(carouselFigure);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public List<CarouselFigure> getCarouselfigures() throws Exception {
        Connection conn = DBUtils.getConn();
        carouselfigureDao = SpringUtils.getBean(CarouselfigureDao.class);
        List<CarouselFigure> carouselfigures = carouselfigureDao.getCarouselfigures();
        DBUtils.close(conn);
        return carouselfigures;
    }
}
