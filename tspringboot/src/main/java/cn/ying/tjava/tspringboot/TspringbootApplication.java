package cn.ying.tjava.tspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication: 包含以下注解：
 *      @SpringBootConfiguration: 指定当前类为配置类
 *          @Configuration
 *      @EnableAutoConfiguration: 启用自动配置
 *          @AutoConfigurationPackage
 *      @ComponentScan: 自动扫描
 */


@SpringBootApplication
public class TspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TspringbootApplication.class, args);
    }

}
