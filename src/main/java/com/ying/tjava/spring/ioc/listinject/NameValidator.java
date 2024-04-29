package com.ying.tjava.spring.ioc.listinject;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class NameValidator implements Validator{
    @Override
    public void validate(String name) {
        System.out.println("name 校验成功!");
    }
}
