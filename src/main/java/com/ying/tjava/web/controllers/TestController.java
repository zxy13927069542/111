package com.ying.tjava.web.controllers;

import com.ying.tjava.web.framework.GetMapping;
import com.ying.tjava.web.framework.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TestController {
    @GetMapping("/getstudents")
    public ModelAndView getStudents(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (name == null) {
            resp.setHeader("Content-Type", "application/json");
            PrintWriter writer = resp.getWriter();
            var body = """
                    {
                        "code": "400", 
                        "message": "缺少name参数"
                    }""";
            writer.write(body);
            writer.flush();
            return null;
        } else {
            resp.setHeader("Content-Type", "application/json");
            PrintWriter writer = resp.getWriter();
            var body = """
                    {
                        "code": "200", 
                        "message": "Hello, %s"
                    }""".formatted(name);
            writer.write(body);
            writer.flush();
        }
        return null;
    }
}
