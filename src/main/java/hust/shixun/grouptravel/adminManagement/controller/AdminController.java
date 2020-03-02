package hust.shixun.grouptravel.adminManagement.controller;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.adminManagement.service.AdminService;

import hust.shixun.grouptravel.config.TimeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
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

//后台
    @RequestMapping("/admin/queryAlladmin")
    public String queryAllAlladmin(Model model){
        List<Admin> admins = adminService.queryAllAdmin();
        model.addAttribute("admins",admins);
        return  "pages/jueseManage/jueSeManagement";

    }

//    后台
    @RequestMapping("/admin/addAdmin")
    public String addAdmin(Admin admin){
        adminService.addAdmin(admin);
        return "redirect:/admin/queryAlladmin";
    }

//后台
    @RequestMapping("/admin/deleteAdmin{id}")
    public String deleteAdmin(@PathVariable Integer id){
        adminService.deleteAdminById(id);
        return "redirect:/admin/queryAlladmin";
    }

//    后台
    @PostMapping("/admin/updateAdmin")
    public String updateAdmin(Admin admin){
        adminService.updateAdmin(admin);
        return "redirect:/admin/queryAlladmin";
    }


//  后台
    @GetMapping("/admin/updateAdmin{id}")
    public String updateAdmin(@PathVariable Integer id, Model model) {
        Admin admin = adminService.queryAdminById(id);
        model.addAttribute("adm",admin);
        return "pages/jueseManage/jueSeEdit";
    }

}
