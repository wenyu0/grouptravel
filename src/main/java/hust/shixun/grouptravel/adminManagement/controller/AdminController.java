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
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping ("/login")
    public String login(Admin admin, Map<String,Object> map,HttpSession session){
        String adminName=admin.getAdminName();
        String adminPassword=admin.getAdminPassword();
        System.out.println(adminName+","+adminPassword);
       Admin admin_1=adminService.login(adminName, adminPassword) ;
       if(admin_1!=null){
           session.setAttribute("adminUser", admin_1);
           return "main";
       }else{
           map.put("msg", "用户名密码错误");
           return "login";
       }
    }

    @RequestMapping("/")
    public String index(){
        return "login";
    }
}
