package com.ying.tjava.annotation;

import java.lang.annotation.*;

/**
 * 元注解用于修饰注解
 * 
 * @Target 用于描述注解的使用对象
 * @Target 最常用的元注解是@Target。使用@Target可以定义Annotation能够被应用于源码的哪些位置：
 *         类或接口：ElementType.TYPE 字段：ElementType.FIELD 方法：ElementType.METHOD
 *         构造方法：ElementType.CONSTRUCTOR 方法参数：ElementType.PARAMETER
 * @Target的参数value可以传数组
 * @Target({ElementType.FIELD, ElementType.METHOD})
 */
@Target(ElementType.TYPE)

/**
 * @Retention 用于定义注解的生命周期: 仅编译期：RetentionPolicy.SOURCE；
 *            仅class文件：RetentionPolicy.CLASS 运行期：RetentionPolicy.RUNTIME
 *            默认为class
 * 
 */
@Retention(RetentionPolicy.RUNTIME)


/**
 * @Repeatable 定义注解是否可重复
 * 需要定义一个@Reportss 它的value值是Report[]
 */
@Repeatable(Reportss.class)

/**
 * @Inherited 定义子类是否继承父类的注解
 * @Inherited仅针对@Target(ElementType.TYPE)类型的annotation有效
 */
public @interface Report {
	int type() default 0;

	Level level() default Level.INFO;

	String value() default "";
}
