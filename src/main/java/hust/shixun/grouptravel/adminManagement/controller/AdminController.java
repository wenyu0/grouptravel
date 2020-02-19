package hust.shixun.grouptravel.adminManagement.controller;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.adminManagement.service.AdminService;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.orderManagerment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping ("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println(username+","+password);
       Admin admin=adminService.login(username, password) ;
       if(admin!=null){
           return "success";
       }else{
           return "login";
       }
    }

    @RequestMapping("/")
    public String index(){
        return "login";
    }
}
