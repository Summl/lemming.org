package com.lemming.lemming.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDao {
    static class DBConfig {
        public static String ClassName = "com.mysql.cj.jdbc.Driver";
        public static String Host = "120.48.35.85";
        public static int Port = 3306;
        public static String DBName = "lemming";
        public static String User = "root";
        public static String Password = "root";
        public static String CharacterEncoding = "UTF-8";
        public static String Url(){
            return String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s",Host,Port,DBName,CharacterEncoding);
        }
    }

    static Connection conn;
    static {
        reconnect();
    }

    /**
     * 重新连接数据库
     */
    private static void reconnect(){
        try {
            Class.forName(DBConfig.ClassName);
            conn = DriverManager.getConnection(DBConfig.Url(), DBConfig.User, DBConfig.Password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 获取向数据库的连接
     * @return 若成功返回Connection，若失败返回null
     */
    public static Connection getConnection() {

        try {
            // 如果链接已被断开，则重新链接
            if (conn.isClosed()){
                reconnect();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
