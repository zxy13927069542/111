package com.ying.tjava.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 通过@WebServlet绑定路由
 */
@WebServlet("/")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //  get path param
        String name = req.getParameter("name");
        //  302 暂时重定向 浏览器重新发一个请求给重定向地址
        //  301 如果服务器发送301永久重定向响应，浏览器会缓存/hi到/hello这个重定向的关联，下次请求/hi的时候，浏览器就直接发送/hello请求了
        //  resp.sendRedirect("/hello" + (name == null ? "" : "?name=" + name));
        //  resp.sendRedirect("https://www.baidu.com/");    //  ok!

        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);   //  301状态码
        resp.setHeader("Location", "/hello" + (name == null ? "" : "?name=" + name)); //  重定向链接
    }
}
