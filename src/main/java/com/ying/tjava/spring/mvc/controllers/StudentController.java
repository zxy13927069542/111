package com.ying.tjava.spring.mvc.controllers;

import com.ying.tjava.spring.jdbc.Student;
import com.ying.tjava.spring.jdbc.StudentService;
import com.ying.tjava.spring.mvc.controllers.entity.ListCondition;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @PostMapping(value = "/list", produces = "application/json", consumes = "application/json")
 *          可以通过produces指定输入的类型，consumes指定输出的类型
 *          并搭配@RequestBody 直接将输入的json转换为实体类
 *          搭配@ResponseBody 直接将实体类转换为json
 */

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

    @PostMapping(value = "/list", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public List<Student> list(@RequestBody ListCondition cond) {
        return studentService.list(cond.getStudent(), cond.getPage(), cond.getLimit());
    }
}


