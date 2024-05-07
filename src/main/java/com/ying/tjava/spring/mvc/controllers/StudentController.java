package com.ying.tjava.spring.mvc.controllers;

import com.ying.tjava.spring.jdbc.Student;
import com.ying.tjava.spring.jdbc.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/getById")
    @ResponseBody
    public Student getStudentById(
            @RequestParam("id") long id,
            HttpSession session) {
        return studentService.getStudentById1(id);
    }
}
