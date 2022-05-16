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
    public User getUserById(String id) throws Exception {
        return JdbcUtils.getBean(conn, User.class, "select * from s_user where id = ?", id);
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

    @Override
    public void updateUserPwd(String id, String password) throws SQLException {
        JdbcUtils.excute(conn, "update s_user set password = ? where id = ?", password, id);
    }

    @Override
    public void addUser(User user) throws SQLException {
        JdbcUtils.excute(conn, "insert  into s_user(id, username, password, type) values (?,?,?,?)", user.getId(), user.getUsername(), user.getPassword(), user.getType());
    }

    @Override
    public void updateUser(User user) throws SQLException {
        JdbcUtils.excute(conn,"update s_user set username = ?, password = ? where id = ?", user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public void deleteUser(String id) throws SQLException {
        JdbcUtils.excute(conn, "delete from s_user where id = ?", id);
    }
}
