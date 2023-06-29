package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//@RefreshScope
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


//    // 注入nacos中的配置属性
    @Autowired
    private PatternProperties properties;
//    @Value("${pattern.dateformat}")
//    private String dateformat;
    // 编写controller，通过日期格式化器来格式化现在时间并返回
    @GetMapping("prop")
    public PatternProperties properties(){
        return properties;
    }
    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat(), Locale.CHINA));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,
    @RequestHeader(value = "truth", required = false) String truth
    ) {
        System.out.println(truth);
        return userService.queryById(id);
    }
}
