package cn.ying.tjava.tspringboot.mvc.controllers;

import cn.ying.tjava.tspringboot.models.dao.Student;
import cn.ying.tjava.tspringboot.mvc.entity.ListCondition;
import cn.ying.tjava.tspringboot.services.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  @PostMapping(value = "/list", produces = "application/json", consumes = "application/json")
 *          可以通过produces指定输入的类型，consumes指定输出的类型
 *          并搭配@RequestBody 直接将输入的json转换为实体类
 *          搭配@ResponseBody 直接将实体类转换为json
 *          需要引入jackson-databind依赖
 *
 * @RestController: 不再需要手动指定produces和consumes
 *                  但仍需@RequestBody，
 *                  @ResponseBody可以省略
 *                  需要引入jackson-databind依赖，但不能引入xml的依赖，否则可能会返回xml而不是json
 *
 * @JsonIgnore: 可以通过该注解忽略字段，既不会被输出，也不会被输入
 * @JsonProperty: 可以通过该注解的access属性来控制字段的输入和输出
 *                  @JsonProperty(access = Access.WRITE_ONLY): 只写
 *                  @JsonProperty(access = Access.READ_ONLY): 只读
 *
 * 跨域问题：跨域问题仅存在于浏览器访问，使用postman等工具并不存在跨域问题
 * 可以通过@CrossOrigin("http://localhost.com:8080")解决
 * 可以通过在WebMvcConfigurer进行配置解决，如：
 *      @Bean
 *      WebMvcConfigurer createWebMvcConfigurer() {
 *          return new WebMvcConfigurer() {
 *              @Override
 *              public void addCorsMappings(CorsRegistry registry) {
 *                  registry.addMapping("/api/**")
 *                     .allowedOrigins("http://local.liaoxuefeng.com:8080")
 *                     .allowedMethods("GET", "POST")
 *                     .maxAge(3600);
 *                   // 可以继续添加其他URL规则:
 *                  // registry.addMapping("/rest/v2/**")...
 *         }
 *     };
 * }
 * postman可以通过添加消息头如Origin:http://localhost.com:8080来测试接口是否支持跨域
 */

@CrossOrigin("http://localhost.com:8080")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Value("${spring.datasource.password}")
    String password;

    @Value("${spring.datasource.username}")
    String username;

    @GetMapping("/getById")
    @ResponseBody
    public Student getStudentById(
            @RequestParam("id") long id,
            HttpSession session) {
        return studentService.getStudentById1(id);
    }

    //  路径变量搭配get方法获取
    @GetMapping("/{id}")
    public Student get(@PathVariable("id") long id) {
        return studentService.getStudentById1(id);
    }

    @PostMapping(value = "/list", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public List<Student> list(@RequestBody ListCondition cond) {
        return studentService.list(cond.getStudent(), cond.getPage(), cond.getLimit());
    }

    @PostMapping("/list1")
    public List<Student> list1(@RequestBody ListCondition cond) {
        return studentService.list(cond.getStudent(), cond.getPage(), cond.getLimit());
    }
}


