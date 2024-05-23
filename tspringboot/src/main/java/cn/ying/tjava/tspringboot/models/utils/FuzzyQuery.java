package cn.ying.tjava.tspringboot.models.utils;

import java.lang.annotation.*;

/**
 * if value == "fuzzy" 表示该字段进行模糊查询
 * else 进行精确查询
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FuzzyQuery {
    String value();
}
