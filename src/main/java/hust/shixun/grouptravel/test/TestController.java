package hust.shixun.grouptravel.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
    @RequestMapping("/login")
    public String login2(){
        return "login";
    }
}
