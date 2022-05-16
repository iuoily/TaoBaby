package com.taobaby.dao;

import com.taobaby.pojo.User;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * 分页查询用户信息
     * @param page 当前页
     * @param size 每页条数
     * @return 分页用户数据
     * @throws Exception sql
     */
    List<User> getUserList(Integer page, Integer size) throws Exception;

    Integer countAll() throws SQLException;
}
