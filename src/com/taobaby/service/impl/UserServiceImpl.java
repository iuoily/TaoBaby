package com.taobaby.service.impl;

import com.taobaby.dao.UserDao;
import com.taobaby.dao.impl.UserDaoImpl;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.User;
import com.taobaby.service.UserService;
import com.taobaby.utils.EncryptionUtils;
import com.taobaby.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/11.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = null;

    @Override
    public String login(String username, String password) throws Exception {
        Connection conn = JdbcUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user = userDao.getUser(username);
        JdbcUtils.close(conn);
        if (null == user) {
            return "用户不存在！";
        }
        if (!EncryptionUtils.encryptMD5(password).equals(user.getPassword())) {
            return "密码错误";
        }
        return null;
    }

    @Override
    public Page<User> getUserPage(Integer page, Integer size) throws Exception {
        Connection conn = JdbcUtils.getConn();
        userDao = new UserDaoImpl(conn);
        List<User> userList = userDao.getUserList(page, size);
        Integer total = userDao.countAll();
        JdbcUtils.close(conn);
        return new Page<User>(page, size, total, userList);
    }

    @Override
    public User getUser(String id) throws Exception {
        Connection conn = JdbcUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user = userDao.getUserById(id);
        JdbcUtils.close(conn);
        return user;
    }

    @Override
    public String changePassword(String username, String oldPassword, String newPassword) throws Exception {
        Connection conn = JdbcUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user = userDao.getUser(username);
        if (null == user) {
            return "用户不存在！";
        }
        if (!EncryptionUtils.encryptMD5(oldPassword).equals(user.getPassword())) {
            return "原密码错误！";
        }
        userDao.updateUserPwd(user.getId(), EncryptionUtils.encryptMD5(newPassword));
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String addUser(User user) throws Exception {
        Connection conn = JdbcUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User u2 = userDao.getUser(user.getUsername());
        if (null != u2) {
            return "用户已存在！";
        }
        userDao.addUser(user);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String updateUser(User user) throws Exception {
        Connection conn = JdbcUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User u2 = userDao.getUser(user.getUsername());
        if (null != u2) {
            return "用户已存在！";
        }
        userDao.updateUser(user);
        JdbcUtils.close(conn);
        return null;
    }

    @Override
    public String deleteUser(String id) throws SQLException {
        Connection conn = JdbcUtils.getConn();
        userDao = new UserDaoImpl(conn);
        userDao.deleteUser(id);
        JdbcUtils.close(conn);
        return null;
    }
}
