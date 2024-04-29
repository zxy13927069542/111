package com.ying.tjava.spring.ioc.listinject;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class EmailValidator implements Validator{
    @Override
    public void validate(String email) {
        System.out.println("email 校验成功！");
    }
}
