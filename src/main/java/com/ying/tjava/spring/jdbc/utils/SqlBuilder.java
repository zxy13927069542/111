package com.ying.tjava.spring.jdbc.utils;

import com.ying.tjava.spring.jdbc.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SqlBuilder {
    public String buildListSql(@Param("student") Student student, @Param("offset") int offset, @Param("limit") int limit) {
        SQL sql = new SQL().SELECT("*").FROM("students");
        if (student.getId() != 0) {
            sql.WHERE("id = #{student.id}");
        }
        if (student.getName() != null) {
            sql.WHERE("name like CONCAT('%', #{student.name}, '%')");
        }
        if (student.getGrade() != 0) {
            sql.WHERE("grade = #{student.grade}");
        }
        if (student.getGender() != 0) {
            sql.WHERE("gender = #{student.gender}");
        }
        if (student.getScore() != 0) {
            sql.WHERE("score = #{student.score}");
        }
        return sql.OFFSET("#{offset}").LIMIT("#{limit}").toString();
    }

//    public String buildListSql(@Param("student") Student student, @Param("offset") int offset, @Param("limit") int limit) {
//
//    }
}
