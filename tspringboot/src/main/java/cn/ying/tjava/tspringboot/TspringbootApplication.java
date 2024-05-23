package cn.ying.tjava.tspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @SpringBootApplication: 包含以下注解：
 *      @SpringBootConfiguration: 指定当前类为配置类
 *          @Configuration
 *      @EnableAutoConfiguration: 启用自动配置
 *          @AutoConfigurationPackage
 *      @ComponentScan: 自动扫描
 */


@SpringBootApplication
@MapperScan()
public class TspringbootApplication {

    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TspringbootApplication.class, args);

    }

}
