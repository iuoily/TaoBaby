package com.taobaby.service.impl;

import com.taobaby.dao.UserDao;
import com.taobaby.dao.impl.UserDaoImpl;
import com.taobaby.pojo.User;
import com.taobaby.service.UserService;
import com.taobaby.utils.EncryptionUtils;

/**
 * @author iuoily on 2022/5/11.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public String login(String username, String password) throws Exception {
        User user = userDao.getUser(username);
        if (null == user) {
            return "用户不存在！";
        }
        if (!EncryptionUtils.encryptMD5(password).equals(user.getPassword())) {
            return "密码错误";
        }
        return null;
    }
}
