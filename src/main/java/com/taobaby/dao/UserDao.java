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
    User getUser(String username, Integer type) throws Exception;

    /**
     *  根据id获取用户
     * @param id 用户名
     * @return 用户
     */
    User getUserById(String id) throws Exception;

    /**
     * 分页查询用户信息
     * @param page 当前页
     * @param size 每页条数
     * @return 分页用户数据
     * @throws Exception sql
     */
    List<User> getUserList(Integer page, Integer size) throws Exception;

    /**
     * 统计所有数据
     * @return 所有数据条数
     * @throws SQLException sql
     */
    Integer countAll() throws SQLException;

    /**
     * 根据Id修改用户密码
     * @param id id
     * @param password 密码
     */
    void updateUserPwd(String id, String password) throws SQLException;

    /**
     * 新增用户
     * @param user 用户
     */
    void addUser(User user) throws SQLException;

    /**
     * 修改用户信息
     * @param user 用户信息
     */
    void updateUser(User user) throws SQLException;

    /**
     * 删除用户信息
     * @param id id
     */
    void deleteUser(String id) throws SQLException;

}
