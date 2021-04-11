package com.form;

import java.sql.*;

/**
 * @author Form      J
 *
 * 链接数据库
 */
public class ConnectionJavaDb {

    private static final String URL = "jdbc:mysql://localhost:3306/javadb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "java";
    private static final String PASSWORD = "123456";
    private String sql;
    private static Connection conn = null;

    static {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
