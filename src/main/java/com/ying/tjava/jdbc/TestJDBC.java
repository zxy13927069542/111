package com.ying.tjava.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {
    private String url;
    private String user;
    private String password;

    //  JDBC连接池
    private static DataSource ds;

    //  初始化连接池
    public void initDataSource() {
        // create a connection pool
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(url);
        cfg.setUsername(user);
        cfg.setPassword(password);
        cfg.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        cfg.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        cfg.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        ds = new HikariDataSource(cfg);
    }

    //  读取properties配置文件
    public TestJDBC() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/db.properties"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            initDataSource();

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
             // 插入并获取新增记录的id
             PreparedStatement ps = conn.prepareStatement("insert into students(name, gender, grade, score) values (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setObject(1, "郑晓颖");
            ps.setObject(2, 1);
            ps.setObject(3, 2);
            ps.setObject(4, 96);
            ps.executeUpdate();
            //  新增记录的ID也是通过ResultSet返回
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    System.out.printf("新增记录ID：%d\n", rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("update students set name = ? where name = ?")
        ) {
            stmt.setObject(1, "郑兵颖");
            stmt.setObject(2, "郑晓颖");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDel() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("delete from students where id = ?")
        ) {
            stmt.setObject(1, 12);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void useTransaction() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            //  开启事务
            conn.setAutoCommit(false);
            insert(conn, "郑州山", 1, 2, 66);
            update(conn, "郑州山", "郑周珊");
            //  提交事务
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e.printStackTrace();
            }

        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    //  关闭事务
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void insert(Connection conn, String name, int gender, int grade, int score) {
        try (PreparedStatement ps = conn.prepareStatement("insert into students(name, gender, grade, score) values (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, name);
            ps.setObject(2, gender);
            ps.setObject(3, grade);
            ps.setObject(4, score);
            ps.executeUpdate();
            //  新增记录的ID也是通过ResultSet返回
            try (ResultSet rs = ps.getGeneratedKeys()) {
                while (rs.next()) {
                    System.out.printf("新增记录ID：%d\n", rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Connection conn, String nameBefore, String nameAfter) {
        try (PreparedStatement stmt = conn.prepareStatement("update students set name = ? where name = ?")
        ) {
            stmt.setObject(1, nameAfter);
            stmt.setObject(2, nameBefore);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertBatch() {
        Student[] stus = new Student[]{
                new Student("郑11", 1, 1, 96),
                new Student("郑22", 1, 1, 96),
                new Student("郑33", 1, 1, 96),
        };

        executeBatchInsert(stus);
    }

    public PreparedStatement prepareInsert(Connection conn) throws SQLException {
        return conn.prepareStatement("insert into students(name, gender, grade, score) values (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
    }

    public void bindParam(PreparedStatement stmt, Student[] stus) throws SQLException {
        for (Student s : stus) {
            stmt.setObject(1, s.getName());
            stmt.setObject(2, s.getGender());
            stmt.setObject(3, s.getGrade());
            stmt.setObject(4, s.getScore());
            stmt.addBatch();
        }
    }

    public void executeBatchInsert(Student[] stus) {
        Connection conn = null;
        try {
            conn = getConn();
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = prepareInsert(conn)) {
                bindParam(stmt, stus);

                //  一个失败，全部退回
                int[] results = stmt.executeBatch();
                for (int r : results) {
                    if (r == Statement.EXECUTE_FAILED) {
                        throw new SQLException("one or more insert failed!");
                    }
                }
                conn.commit();
            }
        } catch (SQLException e) {
            release(conn);
            e.printStackTrace();
        }
    }

    public Connection getConn() throws SQLException {
        return ds.getConnection();
    }

    public void release(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

class Student {
    private long id;
    private String name;
    private int gender;
    private int grade;
    private int score;

    public Student(String name, int gender, int grade, int score) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public int getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }
}
