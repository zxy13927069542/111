package com.ying.tjava.spring.ioc.listinject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validators {
    @Autowired
    private List<Validator> validators;

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }

    public List<Validator> getValidators() {
        return validators;
    }
}
