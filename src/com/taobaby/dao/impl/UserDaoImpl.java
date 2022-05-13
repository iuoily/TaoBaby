package com.taobaby.dao.impl;

import com.taobaby.dao.UserDao;
import com.taobaby.pojo.User;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;

/**
 * @author iuoily on 2022/5/11.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User getUser(String username) throws Exception {
        Connection conn = JdbcUtils.getConn();
        User user = JdbcUtils.getBean(conn, User.class, "select * from s_user where username = ?", username);
        JdbcUtils.close(conn);
        return user;
    }
}
