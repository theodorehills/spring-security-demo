package wang.zihlu.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController
 *
 * @author Zihlu Wang
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "admin";
    }

    @GetMapping("/user")
    public String userApi() {
        return "user";
    }

    @GetMapping("/app")
    public String appApi() {
        return "app";
    }

    @GetMapping("/unauthorised")
    public String unauthorised() {
        return "unauthorised";
    }

}
