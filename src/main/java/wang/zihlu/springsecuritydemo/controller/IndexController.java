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

}
