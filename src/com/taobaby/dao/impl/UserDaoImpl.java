package com.taobaby.dao.impl;

import com.taobaby.dao.UserDao;
import com.taobaby.pojo.User;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/11.
 */
public class UserDaoImpl implements UserDao {

    private Connection conn;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User getUser(String username) throws Exception {
        return JdbcUtils.getBean(conn, User.class, "select * from s_user where username = ?", username);
    }

    @Override
    public List<User> getUserList(Integer page, Integer size) throws Exception {
        return JdbcUtils.getBeanList(conn, User.class, "select * from s_user limit ?,?", (page-1)*size, size);
    }

    @Override
    public Integer countAll() throws SQLException {
        ResultSet resultSet = JdbcUtils.excuteQuery(conn, "select count(id) id from s_user");
        resultSet.next();
        return resultSet.getInt("id");
    }
}
