package com.taobaby.dao;

import com.taobaby.pojo.User;

/**
 * @author iuoily on 2022/5/11.
 */
public interface UserDao {
    /**
     *  根据username获取用户
     * @param username 用户名
     * @return 用户
     */
    User getUser(String username) throws Exception;
}
