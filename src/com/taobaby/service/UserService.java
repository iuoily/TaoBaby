package com.taobaby.service;

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
}
