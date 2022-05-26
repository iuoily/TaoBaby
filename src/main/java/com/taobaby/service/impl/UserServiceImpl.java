package com.taobaby.service.impl;

import com.taobaby.dao.UserDao;
import com.taobaby.dao.impl.UserDaoImpl;
import com.taobaby.pojo.Page;
import com.taobaby.pojo.User;
import com.taobaby.service.UserService;
import com.taobaby.utils.EncryptionUtils;
import com.taobaby.utils.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author iuoily on 2022/5/11.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = null;

    @Override
    public String login(String username, String password, Integer type) throws Exception {
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user = userDao.getUser(username, type);
        DBUtils.close(conn);
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
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        List<User> userList = userDao.getUserList(page, size);
        Integer total = userDao.countAll();
        DBUtils.close(conn);
        return new Page<User>(page, size, total, userList);
    }

    @Override
    public User getUser(String id) throws Exception {
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user = userDao.getUserById(id);
        DBUtils.close(conn);
        return user;
    }

    @Override
    public String changePassword(String username, String oldPassword, String newPassword) throws Exception {
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user = userDao.getUser(username,0);
        if (null == user) {
            return "用户不存在！";
        }
        if (!EncryptionUtils.encryptMD5(oldPassword).equals(user.getPassword())) {
            return "原密码错误！";
        }
        userDao.updateUserPwd(user.getId(), EncryptionUtils.encryptMD5(newPassword));
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String addUser(User user) throws Exception {
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user1 = userDao.getUser(user.getUsername(), user.getType());
        if (user1 != null) {
            if (user1.getType().equals(user.getType())) {
                return "用户已存在！";
            }
        }
        userDao.addUser(user);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String updateUser(User user) throws Exception {
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user1 = userDao.getUser(user.getUsername(), user.getType());
        if (user1 != null) {
            User old = userDao.getUserById(user.getId());
            if (!user1.getId().equals(old.getId())) {
                return "用户名已存在！";
            }
        }
        userDao.updateUser(user);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public String deleteUser(String id) throws SQLException {
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        userDao.deleteUser(id);
        DBUtils.close(conn);
        return null;
    }

    @Override
    public User getUserByName(String username, Integer num) throws Exception {
        Connection conn = DBUtils.getConn();
        userDao = new UserDaoImpl(conn);
        User user = userDao.getUser(username, num);
        DBUtils.close(conn);
        return user;
    }
}