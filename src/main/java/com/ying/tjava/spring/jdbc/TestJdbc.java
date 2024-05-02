package com.ying.tjava.spring.jdbc;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJdbc {
    private AnnotationConfigApplicationContext context;
    private StudentService service;

    @BeforeEach
    public void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        service = context.getBean(StudentService.class);
    }

    @Test
    public void testGetStudentById() {
        Student stu = service.getStudentById1(3);
        assertEquals(3, stu.getId());
        assertEquals("小军112", stu.getName());
        assertEquals(0, stu.getGender());
        assertEquals(1, stu.getGrade());
        assertEquals(93, stu.getScore());
    }

    @Test
    public void testGetStudentByName() {
        Student stu = service.getStudentByName1("小军112");
        assertEquals(3, stu.getId());
        assertEquals("小军112", stu.getName());
        assertEquals(0, stu.getGender());
        assertEquals(1, stu.getGrade());
        assertEquals(93, stu.getScore());
    }

    @Test
    public void testGetStudentByScore() {
        Student stu = service.getStudentByScore1(93);
        assertEquals(3, stu.getId());
        assertEquals("小军112", stu.getName());
        assertEquals(0, stu.getGender());
        assertEquals(1, stu.getGrade());
        assertEquals(93, stu.getScore());
    }

    @Test
    public void testListByGrade() {
        List<Student> students = service.ListByGrade1(1);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testUpdate() {
        Student student = new Student(3, "小军1123", 0, 1, 93);
        service.update1(student);
    }

    @Test
    public void testInsert() {
        Student student = new Student("小军333", 0, 1, 93);
        service.insert(student);
        System.out.println(student);
    }

    @Test
    public void testDeleteById() {
        service.deleteById(14);
    }

}
