package com.ying.tjava.spring.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("userService")
public class UserService {
    @Autowired
    private MailService mailService;
    @Autowired
    private DataSource dataSource;

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //  模拟业务代码
    public void bussinessCode() throws SQLException {
        Connection conn = dataSource.getConnection();
        System.out.println(conn.isClosed() ? "连接已关闭" : "连接已打开");
        System.out.println(mailService != null ? "mailService已注入" : "mailService未注入");
    }
}
