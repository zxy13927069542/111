package com.ying.tjava.spring.ioc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 如果单纯使用注解，由于HikariDataSource是在JAR包里面，所以需要单独用@Bean注解来注册
 * 或者可以用application.xml进行注册(更推荐)
 */

@Configuration
public class Utils {
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/tjava");
        ds.setUsername("root");
        ds.setPassword("066311");
        ds.setAutoCommit(true);
        ds.setMaximumPoolSize(10);
        return ds;
    }
}
