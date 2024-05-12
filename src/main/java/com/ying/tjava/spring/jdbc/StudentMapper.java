package com.ying.tjava.spring.jdbc;

import com.ying.tjava.spring.jdbc.utils.SqlBuilder;
import com.ying.tjava.spring.jdbc.utils.SqlCondition;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface StudentMapper {
    @Select("select * from students where id = #{id}")
    Student getStudentById(@Param("id") long id);

    @Select("select * from students where name = #{name}")
    Student getStudentByName(@Param("name") String name);

    @Select("select * from students where score = #{score}")
    Student getStudentByScore(@Param("score") int score);

    @Select("select * from students where grade = #{grade} limit #{limit} offset #{offset}")
    List<Student> ListByGrade(@Param("grade") int grade, @Param("offset") int offset, @Param("limit") int limit);

    @SelectProvider(type = SqlBuilder.class, method = "buildListSql")
    List<Student> list(@Param("student") Student student, @Param("offset") int offset, @Param("limit") int limit);

    @Update("update students set name = #{stu.name}, grade = #{stu.grade}, gender = #{stu.gender}, score = #{stu.score} where id = #{stu.id}")
    void update(@Param("stu") Student stu);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into students(name, gender, grade, score) values(#{stu.name}, #{stu.gender}, #{stu.grade}, #{stu.score})")
    void insert(@Param("stu") Student stu);

    @Delete("delete from students where id = #{id}")
    void deleteById(long id);
}
