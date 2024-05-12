package com.ying.tjava.spring.jdbc.utils;

public class SqlCondition {
    private String sql;
    private Object[] params;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public SqlCondition(String sql, Object[] params) {
        this.sql = sql;
        this.params = params;
    }

    public SqlCondition() {
    }


}
