package com.ying.tjava.spring.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * Spring IOC
 * 需要添加依赖：org.springframework:spring-context:latest
 * 需要编写application.xml配置文件，用于配置bean(可以用注解代替) resources/application.xml
 * ApplicationContext 就是 ioc容器, 他会一次性创建所有的Bean, 它继承了BeanFactory
 * BeanFactory也是Ioc容器，不过它是按需创建，第一次获取Bean时才创建这个Bean(已被废弃)
 *
 * 除了编写application.xml, 也可以通过注解的方式来实现
 * @Component: 可以将一个类注册为Bead,
 *              默认为单例模式，每次注入都是同一个对象，可以通过@Scope来改变，设置后每次都返回新的实例
 * @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
 *
 * @Autowired: 可以自动注册依赖,
 *              (字段注入)不推荐使用, 可能有空指针异常，而且进行单元测试更难，因为需要spring来进行bean的管理
 *              更推荐使用通过构造器进行注入
 *
 * @ComponentScan: 默认扫描当前类所在包及其子包, 所有通常在顶层包
 *                  也可以手动配置要扫描的包 @ComponentScan(basePackages = "com.ying.tjava.spring.ioc")
 *
 * 单独使用注解，需要使用AnnotationConfigApplicationContext来创建容器
 * 注解和配置文件混合使用，可以在配置文件中开启注解扫描
 *
 * @PostConstruct 和 @PreDestroy 可以在Bean被创建时和被摧毁时调用，摧毁时需要显式调用 context.close()
 *
 * @Qualifier: 可以指定注入的Bean名称
 */

@Configuration
@ComponentScan
public class TestIoc {

    @Test
    public void testApplicationContext() throws SQLException {
          useXml();
        //  useAnnotation();
    }

    //  通过配置文件创建Ioc容器
    private void useXml() throws SQLException {
        //  通过配置文件创建Ioc容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        userService.bussinessCode();
        context.close();


        //  找不到对应的依赖
        //  BeanFactory factory = new XmlBeanFactory(new ClassPathResource("application.xml"));
    }

    //  通过注解创建Ioc容器
    private void useAnnotation() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(getClass());
        UserService userService = context.getBean(UserService.class);
        userService.bussinessCode();

    }
}
