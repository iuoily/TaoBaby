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
}
