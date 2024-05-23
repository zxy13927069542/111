package cn.ying.tjava.tspringboot.models;


import cn.ying.tjava.tspringboot.TspringbootApplication;
import cn.ying.tjava.tspringboot.models.dao.Student;
import cn.ying.tjava.tspringboot.services.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class TestJdbc {
    private AnnotationConfigApplicationContext context;
    private StudentService service;

    @BeforeEach
    public void init() {
        context = new AnnotationConfigApplicationContext(TspringbootApplication.class);
        service = context.getBean(StudentService.class);
    }

    @Test
    public void testGetStudentById() {
        Student stu = service.getStudentById1(3);
        Assertions.assertEquals(3, stu.getId());
        Assertions.assertEquals("小军112", stu.getName());
        Assertions.assertEquals(0, stu.getGender());
        Assertions.assertEquals(1, stu.getGrade());
        Assertions.assertEquals(93, stu.getScore());
    }

    @Test
    public void testGetStudentByName() {
        Student stu = service.getStudentByName1("小军112");
        Assertions.assertEquals(3, stu.getId());
        Assertions.assertEquals("小军112", stu.getName());
        Assertions.assertEquals(0, stu.getGender());
        Assertions.assertEquals(1, stu.getGrade());
        Assertions.assertEquals(93, stu.getScore());
    }

    @Test
    public void testGetStudentByScore() {
        Student stu = service.getStudentByScore1(93);
        Assertions.assertEquals(3, stu.getId());
        Assertions.assertEquals("小军112", stu.getName());
        Assertions.assertEquals(0, stu.getGender());
        Assertions.assertEquals(1, stu.getGrade());
        Assertions.assertEquals(93, stu.getScore());
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
