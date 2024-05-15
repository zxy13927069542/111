package com.ying.tjava.spring.mvc.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Interceptor 可以拦截Controller方法而不是servlet，相比servlet，在spring mvc中interceptor更灵活，需要实现HandlerInterceptor接口
 * 可以通过InterceptorRegistry设置拦截范围，参考com.ying.tjava.spring.AppConfig#createWebMvcConfigurer
 */

@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(LogInterceptor.class);

    //  preHandle在Controller方法执行前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(String.format("%s 被访问了!", request.getRequestURI()));
        return true;
    }

    //  postHandle在Controller方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(String.format("%s 访问成功!", request.getRequestURI()));
    }

    //  afterCompletion在Controller方法执行后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(String.format("%s 访问结束!", request.getRequestURI()));
    }
}
