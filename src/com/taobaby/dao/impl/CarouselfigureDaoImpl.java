package com.taobaby.dao.impl;

import com.taobaby.dao.CarouselfigureDao;
import com.taobaby.pojo.CarouselFigure;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarouselfigureDaoImpl implements CarouselfigureDao {

    private Connection conn;

    public CarouselfigureDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Integer countAll() throws SQLException {
        ResultSet resultSet = JdbcUtils.excuteQuery(conn, "select count(id) id from s_carousel_figure");
        resultSet.next();
        return resultSet.getInt("id");
    }

    @Override
    public List<CarouselFigure> getCarouselfigureDao(Integer page, Integer size) throws Exception {
        return JdbcUtils.getBeanList(conn, CarouselFigure.class, "select * from s_carousel_figure limit ?,?", (page-1)*size, size);
    }

    @Override
    public CarouselFigure getCarouselFigure(String id) throws Exception {
        return JdbcUtils.getBean(conn, CarouselFigure.class, "select * from s_carousel_figure where id = ?", id);
    }

    @Override
    public CarouselFigure getCarouselFigure(Integer sequence_num) throws Exception {
        return JdbcUtils.getBean(conn, CarouselFigure.class, "select * from s_carousel_figure where sequence_num = ?", sequence_num);
    }

    @Override
    public void addCarouselFigure(CarouselFigure carouselFigure) throws SQLException {
        JdbcUtils.excute(conn, "insert  into `s_carousel_figure`(`id`,`url`,`sequence_num`) values (?,?,?)", carouselFigure.getId(), carouselFigure.getUrl(), carouselFigure.getSequenceNum());
    }

    @Override
    public void deleteCarouselFigure(String id) throws SQLException {
        JdbcUtils.excute(conn, "delete from s_carousel_figure where id = ?", id);
    }

    @Override
    public void deleteSelectCarouselFigure(String[] ids) throws SQLException {
        StringBuilder sql = new StringBuilder("delete from s_carousel_figure where id in (");
        for (String id : ids) {
            sql.append("?,");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1));
        sql.append(")");
        JdbcUtils.excute(conn, sql.toString(), ids);
    }

    @Override
    public void updateCarouselFigure(CarouselFigure carouselFigure) throws SQLException {
        JdbcUtils.excute(conn, "update s_carousel_figure set url = ? , sequence_num = ? where id = ?", carouselFigure.getUrl(), carouselFigure.getSequenceNum(), carouselFigure.getId());
    }
}
