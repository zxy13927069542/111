package com.ying.tjava.spring.ioc.listinject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自动注入List，要指定顺序，可以通过@Order进行指定
 */

@Configuration
@ComponentScan
public class TestListInject {
    @Test
    public void testListInject() {
        ApplicationContext context = new AnnotationConfigApplicationContext(getClass());
        Validators validators = context.getBean(Validators.class);
        for (Validator v : validators.getValidators()) {
            v.validate("11");
        }
    }
}
