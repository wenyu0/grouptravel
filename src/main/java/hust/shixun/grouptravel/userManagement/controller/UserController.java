package hust.shixun.grouptravel.userManagement.controller;

import hust.shixun.grouptravel.adminManagement.entities.Admin;
import hust.shixun.grouptravel.entities.Notes;
import hust.shixun.grouptravel.entities.Order;
import hust.shixun.grouptravel.entities.Product;
import hust.shixun.grouptravel.entities.User;
import hust.shixun.grouptravel.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/user/addOrder")
    @ResponseBody
    public boolean addOrder(Order order) {
        return userService.addOrder(order);
    }

    @RequestMapping("/user/queryProductById")
    @ResponseBody
    public Product queryProductById(@RequestParam(value = "productId") int productId) {
        return userService.queryProductById(productId);
    }

    @RequestMapping("/user/queryProductByName")
    @ResponseBody
    public List<Product> queryProductByName(@RequestParam(value = "productName") String productName) {
        return userService.queryProductByName(productName);
    }

    @RequestMapping("/user/queryProductByProvince")
    @ResponseBody
    public List<Product> queryProductByProvince(@RequestParam(value = "provinceName") String provinceName) {
        List<Product> products = new ArrayList<>();
        List<Integer> cityList = userService.getCityList(provinceName);
        for(int cityId:cityList)
            products.addAll(userService.queryProductByCity(cityId));
        return products;
    }

    @RequestMapping("/user/queryOrdersWithUnpay")
    @ResponseBody
    public List<Order> queryOrdersWithUnpay(@RequestParam(value = "userId") int userId) {
        return userService.queryOrdersWithUnpay(userId);
    }

    @RequestMapping("/user/updateUnpayOrder")
    @ResponseBody
    public Boolean updateUnpayOrder(@RequestParam(value = "orderId") int orderId) {
        return userService.updateUnpayOrder(orderId);
    }

    @RequestMapping("/user/queryNotesByOrderId")
    @ResponseBody
    public List<Notes> queryNotesByOrderId(int orderId) {
        return userService.queryNotesByOrderId(orderId);
    }

    @RequestMapping("/user/addNotes")
    @ResponseBody
    public Boolean addNotes(Notes notes) {
        return userService.addNotes(notes);
    }

    @RequestMapping("/user/queryOrderPrice")
    @ResponseBody
    public double queryOrderPrice(@RequestParam(value = "orderId") int orderId) {
        return userService.queryOrderPrice(orderId);
    }

    @RequestMapping("/user/updateOrderPrice")
    @ResponseBody
    public Boolean updateOrderPrice(@RequestParam(value = "orderId") int orderId,
                                    @RequestParam(value = "orderPrice") double orderPrice) {
        return userService.updateOrderPrice(orderId,orderPrice);
    }

    @RequestMapping("/user/queryOrder")
    @ResponseBody
    public List<Order> queryOrder(@RequestParam(value = "orderId") int orderId) {
        return userService.queryOrder(orderId);
    }

    @RequestMapping("/user/addRates")
    @ResponseBody
    public Boolean addRates(double rate, int orderId){
        return  userService.addRates(rate,orderId);
    }

    @RequestMapping("/user/likeNotes")
    @ResponseBody
    public Boolean likeNotes(int userId,int notesId){
        return userService.likeNotes(userId,notesId);
    }

    @RequestMapping("/user/commentNotes")
    @ResponseBody
    public Boolean commentNotes(int userId,int notesId,String commentContent){
        return userService.commentNotes(userId,notesId,commentContent);
    }

    @RequestMapping("/user/queryLikeNotes")
    @ResponseBody
    public List<Notes> queryLikeNotes(int userId){
        return userService.queryLikeNotes(userId);
    }


    @RequestMapping("/user/queryAllUser")
    public String queryAllUser(Model model){
        List<User> users = userService.queryAllUser();
        model.addAttribute("users",users);
        return "pages/userManage/userManagement";

    }


    @RequestMapping("/user/deleteUser{id}")
    public String deleteAdmin(@PathVariable Integer id){
        userService.deleteUserById(id);
        return "redirect:/user/queryAllUser";
    }

    @PostMapping("/user/updateUser")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/user/queryAllUser";
    }



    @GetMapping("/user/updateUser{id}")
    public String updateUser(@PathVariable Integer id, Model model) {
        User user = userService.queryUserById(id);
        model.addAttribute("user",user);
        return "pages/userManage/userEdit";
    }

    @RequestMapping("user/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/user/queryAllUser";
    }













}
