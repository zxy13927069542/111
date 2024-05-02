# java 笔记

## spring

### AOP
#### maven依赖
使用spring AOP, 需要导入maven依赖
```maven
<!--spring aop依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>6.0.0</version>
        </dependency>
```
#### 使用方法
我们定义一个```AspectUtils```:
```java
@Aspect
@Component
public class AspectUtils {

    @Before("execution(public * com.ying.tjava.spring.ioc.MailService.*(..))")
    public void doAccessCheck() {
        System.out.println("[Before] do some Access check.");
    }

    @Around("execution(public * com.ying.tjava.spring.ioc.UserService.*(..))")
    public Object startTransaction(ProceedingJoinPoint pjp) {
        System.out.println("[Around] start transaction.");
        Object rtValue = null;
        try {
            rtValue = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println("[Around] commit transaction.");
        return rtValue;
    }
}
```
除了```@Aspect```还需要```@Component```注解，否则无法被spring扫描到。

#### 基于注解确定使用范围
上述的例子通过复杂的匹配表达式来确定使用范围，我们难以确定哪些方法会被注入，所以我们可以通过在要使用切面的方法上添加注解来确定使用范围。  
例如，我们要在方法执行时打印日志，我们可以在方法上添加```@Log```注解，然后通过切面类来确定使用范围。  
先定义一个@Log注解：
```java
@Target(ElementType.METHOD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
```
定义切面类，实现切面逻辑：
```java
@Aspect
@Component
public class LogAspect {

    @Around("@annotation(log)")
    public Object doLog(ProceedingJoinPoint pjp, Log log) throws Throwable {
        System.out.println("[Log] start logging...");
        Object rt = pjp.proceed();
        System.out.println("[Log] end logging.");
        return rt;
    }
}
```
然后，我们在需要使用切面的方法上添加该注解，就使用切面了
```java
@Log
    public void bussinessCode() throws SQLException {
        Connection conn = dataSource.getConnection();
        System.out.println(conn.isClosed() ? "连接已关闭" : "连接已打开");
        System.out.println(mailService != null ? "mailService已注入" : "mailService未注入");
    }
```
运行下试试：
```
[Log] start logging...
连接已打开
mailService已注入
[Log] end logging.
```
