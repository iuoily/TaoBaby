package com.taoBaby.listener;

import com.taoBaby.utils.DBUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author iuoily on 2022/5/18.
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("初始化连接池");
        Connection conn = null;
        try {
            conn = DBUtils.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtils.close(conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("销毁");
    }
}
