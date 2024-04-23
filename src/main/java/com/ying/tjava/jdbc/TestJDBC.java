package com.ying.tjava.jdbc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    private String url;
    private String user;
    private String password;

    //  读取properties配置文件
    public TestJDBC() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/db.properties"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试JDBC连接并执行简单查询。
     * 该方法不接受参数，也不返回任何值。
     * 它将尝试建立数据库连接，执行一个查询，并打印结果。
     */
    @Test
    public void testQuery() {
        System.out.printf("url: %s, user: %s, password: %s\n", url, user, password);
        try (Connection conn = DriverManager.getConnection(url, user, password);
             // 使用preparedStatement而不是createStatement，可以防止SQL注入攻击
             // Statement stmt = conn.createStatement();
             // ResultSet rs = stmt.executeQuery("select * from students where grade = 1;")
             PreparedStatement stmt = conn.prepareStatement("select * from students where grade = ?")) {
            stmt.setObject(1, 1);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("[id: %d, grade: %d, name: %s, gender: %d, score: %d]\n",
                            rs.getLong("id"),
                            rs.getInt("grade"),
                            rs.getString("name"),
                            rs.getInt("gender"),
                            rs.getInt("score"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement("insert into students(name, gender, grade, score) values (?, ?, ?, ?)")
        ) {
            ps.setObject(1, "郑晓颖");
            ps.setObject(2, 1);
            ps.setObject(3, 2);
            ps.setObject(4, 96);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
