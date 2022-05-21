package com.taobaby.utils;

import com.zaxxer.hikari.HikariDataSource;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author iuoily
 * @date 2022/4/19
 * 2022.05.16 V2.1 修复一个小bug getColumnName ==> getColumnLabel at :column 91, 116;
 * 2022.05.18 V2.2
 *
 */
public class DBUtils {
    private static HikariDataSource hikariDataSource;

    static {
        hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql:///eshop?serverTimezone=UTC");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setMaximumPoolSize(100);
    }

    /**
     *  获取连接
     * @return 数据库连接
     * @throws SQLException sql
     */
    public static Connection getConn() throws SQLException {
        return hikariDataSource.getConnection();
    }

    /**
     *  执行
     * @param connection 数据库连接
     * @param sql sql语句
     * @param args 可变长参数
     * @throws SQLException 异常
     */
    public static void execute(Connection connection, String sql, Object... args) throws SQLException {
        setArgs(connection, sql, args).execute();
    }


    /**
     *  执行查询
     * @param connection 数据库连接
     * @param sql sql语句
     * @param args 可变长参数
     * @return 数据库查询结果集
     * @throws SQLException sql
     */
    public static ResultSet executeQuery(Connection connection, String sql, Object... args) throws SQLException {
        return setArgs(connection, sql, args).executeQuery();
    }

    /**
     *  设置参数
     * @param connection 数据库连接
     * @param sql sql语句
     * @param args 可变长参数
     * @return 返回预编译对象
     * @throws SQLException sql
     */
    private static PreparedStatement setArgs(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1, args[i]);
        }
        return preparedStatement;
    }


    /**
     *  获取对象
     * @param tClass 对象的类型
     * @param sql sql语句
     * @param para 查询字段
     * @param <T> 泛型--查询的类
     * @return 单个对象
     * @throws Exception sql classnotfound
     */
    public static <T> T getBean(Connection conn, Class<T> tClass, String sql, Object... para) throws Exception {
        List<T> list = getBeanList(conn, tClass, sql, para);
        if (list.size()==0) {
            return null;
        }
        return list.get(0);
    }

    /**
     *  获取对象集合
     * @param tClass 对象类型
     * @param sql sql语句
     * @param <T> 泛型
     * @return 对象集合
     * @throws Exception sql class
     */
    public static <T> List<T> getBeanList(Connection conn, Class<T> tClass, String sql, Object...para) throws Exception {
        ResultSet resultSet = executeQuery(conn, sql, para);
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            T t = tClass.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String fName = convertField(metaData.getColumnLabel(i));
                Field field = tClass.getDeclaredField(fName);
                if (field==null) {
                    field = tClass.getSuperclass().getDeclaredField(fName);
                }
                field.setAccessible(true);
                if ("DATETIME".equals(metaData.getColumnTypeName(i))) {
                    Object o = resultSet.getObject(metaData.getColumnLabel(i));
                    field.set(t, dateStrToLocalDatetime(o.toString()));
                }else if("DATE".equals(metaData.getColumnTypeName(i))){
                    Object o = resultSet.getObject(metaData.getColumnLabel(i));
                    field.set(t, LocalDate.parse(o.toString()));
                }else {
                    field.set(t, resultSet.getObject(metaData.getColumnLabel(i)));
                }
            }
            list.add(t);
        }
        return list;
    }

    /**
     * 关闭连接
     * @param conn 连接对象
     */
    public static void close(Connection conn){
        hikariDataSource.evictConnection(conn);
    }


    /**
     * 数据库字段转小驼峰
     * @param str
     * @return
     */
    private static String convertField(String str) {
        String[] strArr = str.split("_");
        String rs = strArr[0];
        for (int i = 1; i < strArr.length; i++) {
            String value = strArr[i];
            String name = value.replace(value.substring(0, 1), value.substring(0, 1).toUpperCase());
            rs = rs.concat(name);
        }
        return rs;
    }
	
	 /**
     * 数据库日期转LocalDateTime
     * 2018-09-21 18:19:33.0 ==> LocalDateTime
     * @param date
     * @return
     */
    private static LocalDateTime dateStrToLocalDatetime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        TemporalAccessor temporalAccessor = formatter.parse(date);
        return LocalDateTime.from(temporalAccessor);
    }


}
