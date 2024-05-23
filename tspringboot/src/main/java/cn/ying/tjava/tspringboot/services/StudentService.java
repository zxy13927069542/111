package cn.ying.tjava.tspringboot.services;


import cn.ying.tjava.tspringboot.models.mapper.StudentMapper;
import cn.ying.tjava.tspringboot.models.dao.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
public class StudentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentMapper studentMapper;

    public Student getStudentById(long id) {
        return jdbcTemplate.execute((Connection conn) -> {
            try(PreparedStatement stmt = conn.prepareStatement("select * from students where id = ?")) {
                stmt.setLong(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("gender"), rs.getInt("grade"), rs.getInt("score"));
                    }
                    return null;
                }
            }
        });
    }

    public Student getStudentById1(long id) {
        Student student = studentMapper.getStudentById(id);
        return student;
    }

    public Student getStudentByName(String name) {
        return jdbcTemplate.execute("select * from students where name = ?", (PreparedStatement stmt) -> {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("gender"), rs.getInt("grade"), rs.getInt("score"));
                }
                return null;
            }
        });
    }

    public Student getStudentByName1(String name) {
        return studentMapper.getStudentByName(name);
    }

    public Student getStudentByScore(int score) {
        return jdbcTemplate.queryForObject("select * from students where score = ?", (ResultSet rs, int rowNum) -> {
            return new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("gender"), rs.getInt("grade"), rs.getInt("score"));
        }, score);
    }

    public Student getStudentByScore1(int score) {
        return studentMapper.getStudentByScore(score);
    }

    public List<Student> ListByGrade(int grade) {
        return jdbcTemplate.query("select * from students where grade = ? limit ? offset ?", new BeanPropertyRowMapper<>(Student.class), grade, 10, 0);
    }

    public List<Student> ListByGrade1(int grade) {
        return studentMapper.ListByGrade(grade, 0, 10);
    }

    public List<Student> list(Student student, int page, int limit) {
        return studentMapper.list(student, (page - 1) * limit, limit);
    }

    @Transactional
    public void update(Student stu) {
        if (1 != jdbcTemplate.update("update students set name = ? where id = ?", stu.getName(), stu.getId())) {
            throw new RuntimeException("id not found!");
        }
    }

    public void update1(Student stu) {
        studentMapper.update(stu);
    }

    public void insert(Student student) {
        studentMapper.insert(student);
    }

    public void deleteById(long id) {
        studentMapper.deleteById(id);
    }
}
