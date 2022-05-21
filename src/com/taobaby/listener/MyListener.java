package com.taobaby.listener;

import com.taobaby.utils.DBUtils;
import lombok.SneakyThrows;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

/**
 * @author iuoily on 2022/5/18.
 */
public class MyListener implements ServletContextListener {

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("初始化连接池");
        Connection conn = DBUtils.getConn();
        DBUtils.close(conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("销毁");
    }
}
