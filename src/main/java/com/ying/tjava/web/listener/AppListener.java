package com.ying.tjava.web.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 *
 */

@WebListener
public class AppListener implements ServletContextListener {
    //  程序创建前的回调函数，执行万该函数后才开始接收web请求
    //  可以用于数据库连接的初始化等
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        System.out.println("数据库初始化中...");
        System.out.println("数据库初始化成功！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        System.out.println("数据库连接池销毁中...");
        System.out.println("数据库连接池销毁完成！");
        System.out.println("程序关闭！");
    }
}
