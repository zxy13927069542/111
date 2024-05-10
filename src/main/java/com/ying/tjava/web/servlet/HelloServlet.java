package com.ying.tjava.web.servlet;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/tjava");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("066311");
        ds.setAutoCommit(true);
        ds.setMaximumPoolSize(10);
        try {
            Connection conn = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String name = req.getParameter("name");
        if (name == null) {
            resp.setHeader("Content-Type", "application/json");
            PrintWriter writer = resp.getWriter();
            var body = """
                    {
                        "code": "400", 
                        "message": "缺少name参数"
                    }""";
            writer.write(body);
            writer.flush();
            return;
        } else {
            resp.setHeader("Content-Type", "application/json");
            PrintWriter writer = resp.getWriter();
            var body = """
                    {
                        "code": "200",
                        "message": "Hello, %s"
                    }""".formatted(name);
            writer.write(body);
            writer.flush();
        }
    }

}
