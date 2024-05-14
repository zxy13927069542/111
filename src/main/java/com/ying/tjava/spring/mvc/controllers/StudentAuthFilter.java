package com.ying.tjava.spring.mvc.controllers;

import com.ying.tjava.spring.jdbc.Student;
import com.ying.tjava.spring.jdbc.StudentService;
import jakarta.servlet.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 在servlet中，我们通过@WebServlet来注册filter，并由servlet容器创建它
 * 这种方式对于spring来说是透明的，如果在filter存在需要依赖注入的属性，那么这个属性将为空，因为spring无法注入
 * 如果想要联动spring和servlet容器，则需通过spring mvc提供的DelegatingFilterProxy来实现
 * <web-app>
 *     <filter>
 *         <filter-name>authFilter</filter-name>
 *         <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 *     </filter>
 *
 *     <filter-mapping>
 *         <filter-name>authFilter</filter-name>
 *         <url-pattern>/*</url-pattern>
 *     </filter-mapping>
 * </web-app>
 *
 * 原理如下:
 *      1、Servlet容器从web.xml中读取配置，实例化DelegatingFilterProxy，注意命名是authFilter
 *      2、Spring容器通过扫描@Component实例化AuthFilter。
 *      3、DelegatingFilterProxy自动在spring容器中查找 authFilter，并将请求转发给他
 */


@Component
public class StudentAuthFilter implements Filter {
    private final static Log log = LogFactory.getLog(StudentAuthFilter.class);

    @Autowired
    private StudentService ss;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (ss != null) {
            Student student = ss.getStudentById(3);
            log.info(student);
            log.info("student auth success!");
        }
        chain.doFilter(request, response);
    }
}
