package com.ying.tjava.spring.ioc;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("mailService")
public class MailService {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean [mailService] 初始化...");
        System.out.println("Bean [mailService] 初始化成功!");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Bean [mailService] 开始销毁...");
        System.out.println("Bean [mailService] 销毁成功!");
    }
}
