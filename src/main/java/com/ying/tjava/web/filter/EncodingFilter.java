package com.ying.tjava.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * 类似于golang的middleware中间件
 * Servlet规范并没有对@WebFilter注解标注的Filter规定顺序。
 * 如果一定要给每个Filter指定顺序，就必须在web.xml文件中对这些Filter再配置一遍。
 *
 * @WebFilter("/user/*") 设置过滤组
 */
@WebFilter("/encode/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("EncodingFilter: doFilter");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //  调用后续的过滤器
        chain.doFilter(request, response);
    }
}
