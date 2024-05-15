package com.ying.tjava.spring;


import com.ying.tjava.spring.mvc.controllers.LogInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

/**
 * @EnableTransactionManagement: 开启声明式事务，添加该注释后，不需要在添加@EnableAspectJAutoProxy
 * @Transactional: 通过在方法上添加注释，将该方法自动进行事务管理
 *
 * @MapperScan("com.ying.tjava.spring.jdbc"): 可以让MyBatis自动扫描指定包的所有Mapper并创建实现类
 *
 * spring 集成mybatis：
 *          1、创建 SqlSessionFactoryBean 的Bean方法，参考 com.ying.tjava.spring.jdbc.AppConfig#createSqlSessionFactoryBean(javax.sql.DataSource)
 *          2、创建Mapper接口
 *          3、添加 @MapperScan("com.ying.tjava.spring.jdbc") 注解
 *
 */

@ComponentScan({"com.ying.tjava.spring.jdbc", "com.ying.tjava.spring.mvc"})
@Configuration
@PropertySource("classpath:/db.properties")
@EnableTransactionManagement
@MapperScan()
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    static final Log log = LogFactory.getLog(AppConfig.class);

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String userName;

    @Value("${password}")
    private String password;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //  注册Interceptor拦截器，并设置拦截范围
    @Bean
    public WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LogInterceptor()).addPathPatterns("/student/list");
            }
        };
    }

    //  配置消息转换器，可搭配注解@ResponseBody将对象以json格式输出
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(jacksonMessageConverter());
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
//        return new MappingJackson2HttpMessageConverter();
//    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        HikariDataSource ds = new HikariDataSource();
        //  tomcat 下需要指定驱动，不然会报找不到驱动
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setAutoCommit(true);
        ds.setMaximumPoolSize(10);
        return ds;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * mybatis 数据源
     * @param dataSource
     * @return
     */
    @Bean
    public SqlSessionFactoryBean createSqlSessionFactoryBean(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    //  spring 事务管理器
    @Bean
    public PlatformTransactionManager createTxManager(@Autowired DataSource dataSource) {
        log.info("spring 事务管理器创建成功!");
        return new DataSourceTransactionManager(dataSource);
    }


}
