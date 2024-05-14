package com.ying.tjava.spring.jdbc;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ying.tjava.spring.jdbc.utils.FuzzyQuery;

public class Student {
    private long id;

    @FuzzyQuery("fuzzy")
    private String name;
    private int gender;
    private int grade;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int score;

    public Student() {}

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Student(int id, String name, int gender, int grade, int score) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.score = score;
    }

    public Student(String name, int gender, int grade, int score) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public int getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }
}