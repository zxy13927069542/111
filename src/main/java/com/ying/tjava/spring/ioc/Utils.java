package com.ying.tjava.spring.ioc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 如果单纯使用注解，由于HikariDataSource是在JAR包里面，所以需要单独用@Bean注解来注册
 * 或者可以用application.xml进行注册(更推荐)
 */

@PropertySource("classpath:/db.properties")
@Configuration
public class Utils {
    @Value("${url}")
    private String url;

    @Value("${user}")
    private String userName;

    @Value("${password}")
    private String password;

    @Value("${autoCommit}")
    private boolean autoCommit;

    @Value("${poolSize}")
    private int poolSize;


    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource getDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setAutoCommit(autoCommit);
        ds.setMaximumPoolSize(poolSize);
        return ds;
    }
}
