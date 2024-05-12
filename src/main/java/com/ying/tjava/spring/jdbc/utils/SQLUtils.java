package com.ying.tjava.spring.jdbc.utils;

import com.ying.tjava.spring.jdbc.Student;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLUtils {

    //  通过反射读取object对象并构建sql语句
    public static SqlCondition getSqlCondition(Object object) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<Object> params = new ArrayList<>();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(FuzzyQuery.class)) {
                FuzzyQuery query = f.getAnnotation(FuzzyQuery.class);
                sb.append(" and ").append(f.getName()).append(" like '%?%'");
                params.add(f.get(object));
            } else {
                sb.append(" and ").append(f.getName()).append(" = ?");
                params.add(f.get(object));
            }
        }

        String sqlCond = sb.substring(5).toString();
        return new SqlCondition(sqlCond, params.toArray());
    }

    @Test
    public void test() throws IllegalAccessException {
        Student student = new Student(1, "ying", 1, 1, 1);
        SqlCondition sqlCondition = getSqlCondition(student);
        System.out.println(sqlCondition.getSql());
        System.out.println(Arrays.toString(sqlCondition.getParams()));
    }
}