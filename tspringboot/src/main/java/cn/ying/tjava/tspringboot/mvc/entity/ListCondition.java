package cn.ying.tjava.tspringboot.mvc.entity;

import cn.ying.tjava.tspringboot.models.dao.*;

public class ListCondition {
    private Student student;
    private int page;
    private int limit;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ListCondition(Student student, int page, int limit) {
        this.student = student;
        this.page = page;
        this.limit = limit;
    }

    public ListCondition() {
    }
}