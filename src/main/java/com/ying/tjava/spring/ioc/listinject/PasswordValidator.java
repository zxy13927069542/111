package com.ying.tjava.spring.ioc.listinject;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class PasswordValidator implements Validator{
    @Override
    public void validate(String password) {
        System.out.println("password 校验成功!");
    }
}
