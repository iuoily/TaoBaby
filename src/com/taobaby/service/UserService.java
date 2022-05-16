package com.taobaby.service;

import com.taobaby.pojo.Page;
import com.taobaby.pojo.User;

import java.sql.SQLException;

/**
 * @author iuoily on 2022/5/11.
 */
public interface UserService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    String login(String username, String password) throws Exception;

    /**
     * 分页获取用户数据
     * @param page 页数
     * @param size 条数
     * @return 分页数据
     * @throws Exception sql
     */
    Page<User> getUserPage(Integer page, Integer size) throws Exception;

    User getUser(String id) throws Exception;

    /**
     * 修改密码
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    String changePassword(String username, String oldPassword, String newPassword) throws Exception;

    /**
     * 新增用户
     * @param user 用户
     * @return
     */
    String addUser(User user) throws Exception;

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    String updateUser(User user) throws Exception;

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    String deleteUser(String id) throws SQLException;

}
